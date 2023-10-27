package com.sm.android.utils.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

object MainUtils {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = Objects.requireNonNull(context)
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null
    }

    fun isNetworkWorking(): Boolean {

        return try {
            val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val httpURLConnection: HttpURLConnection = URL("https://www.google.com").openConnection() as HttpURLConnection
            httpURLConnection.setRequestProperty("User-Agent", "Android")
            httpURLConnection.setRequestProperty("Connection", "close")
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.connectTimeout = 1500
            httpURLConnection.readTimeout = 1500
            httpURLConnection.connect()
            Log.e("TAGHomeFragNetwork", "isnetworkworking: ${httpURLConnection.responseCode}")
            httpURLConnection.responseCode == 200
        } catch (e: Exception) {
            false
        }
    }

    private fun <R> CoroutineScope.executeAsyncTask(
        onPreExecute: () -> Unit,
        doInBackground: () -> R,
        onPostExecute: (R) -> Unit
    ) = launch {
        onPreExecute()
        val result = withContext(Dispatchers.IO) { // runs in background thread without blocking the Main Thread
            doInBackground()
        }
        onPostExecute(result)
    }

    fun isNetworkAvailable2(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    fun Context.isInternetConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }


        return false
    }

    inline fun <reified T> Activity.startActivity(finish: Boolean = true) {
        Intent(this, T::class.java).apply {
            startActivity(this)
            if (finish) {
                finish()
            }
        }
    }

    fun Context.showToast(message: String) {
        Handler(Looper.getMainLooper()).post {
            val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
            toast.show()
        }
    }

    fun Context.ShareText(text: String?) {
        try {
            if (text != null) {
                val share = Intent(Intent.ACTION_SEND)
                share.type = "text/plain"
                share.putExtra(Intent.EXTRA_TEXT, text)
                startActivity(
                    Intent.createChooser(
                        share,
                        "Share Text"
                    )
                )
            }
        } catch (e: Exception) {
        }
    }

    fun Context.rateUs(){
        val feedss =
            Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
        val rate = Intent(Intent.ACTION_VIEW, feedss)
        startActivity(rate)
    }
    fun Context.composeEmail() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:ork@gmail.com") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Flash App")
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent)
        }
    }

}