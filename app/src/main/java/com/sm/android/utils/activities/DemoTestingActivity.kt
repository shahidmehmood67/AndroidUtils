package com.sm.android.utils.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.sm.android.utils.R

class DemoTestingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_testing)



        val webView: WebView = findViewById(R.id.webview)

        webView.settings.javaScriptEnabled = true

        // Set a WebViewClient to handle the page navigation
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                // After the page is finished loading, you can execute JavaScript to retrieve the textarea value

                view?.postDelayed({
                    // Execute JavaScript to retrieve the textarea value
                    webView.evaluateJavascript("document.getElementById('edit_1').value", { value ->
                        // 'value' contains the value of the textarea
                        // You can use 'value' in your Android application logic
                        // For example, you can log it or display it in a TextView
                        println("Textarea value: $value")
                        Log.e("Textarea", "onPageFinished: value: $value" )
                    })
                }, 1000) // Adjust the delay as needed
            }
        }

        // Load your HTML content into the WebView
        val htmlContent = """
            <html>
            <head>
                <script src="http://coinmill.com/frame.js"></script>
                <script>
                    var currency_round=true;
                    var currency_decimalSeparator='.';
                    var currency_thousandsSeparator=',';
                    var currency_thousandsSeparatorMin=3;
                </script>
            </head>
            <body>
                <textarea id="edit_1" onchange="document.getElementById('results').innerHTML=currency_convert(this.value,'USD','GBP')"></textarea><br>
                Converted into GBP:<div id="results"></div>
            </body>
            </html>
        """.trimIndent()

        webView.loadDataWithBaseURL(null, htmlContent, "text/html", "utf-8", null)

    }
}