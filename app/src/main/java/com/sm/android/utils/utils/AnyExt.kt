package com.sm.android.utils.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.Drawable
import android.location.Location
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.tabs.TabLayout
//import com.google.gson.Gson
//import com.google.gson.GsonBuilder
//import com.google.gson.JsonElement
//import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream
import java.lang.reflect.Type
import java.text.DecimalFormat
import kotlin.math.roundToInt

@SuppressLint("LogNotTimber")
fun Any.logE(tag: String = "", throwable: Throwable? = null) {
        Log.e("nExt -> $tag", "$this\n", throwable)
}

@SuppressLint("LogNotTimber")
fun Any.logD(tag: String = "", throwable: Throwable? = null) {
        Log.d("nExt -> $tag", "$this\n", throwable)
}

inline fun <reified NEW> Any.cast(): NEW? {
    return if (this.isCastable<NEW>())
        this as NEW
    else
        null
}

inline fun <reified NEW> Any.isCastable(): Boolean {
    return this is NEW
}

inline fun <reified T : Any> T.getClassJava() = T::class.java

/*fun Any.toJson(gson: Gson): String {
    return gson.toJson(this)
}

fun Any.toJsonTree(gson: Gson): JsonElement {
    return gson.toJsonTree(this)
}

inline fun <reified MODEL> String.fromJson(gson: Gson): MODEL? {
    return gson.fromJson(this, MODEL::class.java)
}

inline fun <reified MODEL> String.fromJsonList(gson: Gson): List<MODEL> {
    return gson.fromJson(this, object : TypeToken<ArrayList<MODEL>>() {}.type)
}

fun <T> String.getList(gson: Gson): ArrayList<T>? {
    return gson.fromJson(this, typeOfList<T>())
}

fun Any.toJson(): String {
    return Gson().toJson(this)
}

fun Any.toJsonTree(): JsonElement {
    return Gson().toJsonTree(this)
}

inline fun <reified MODEL> String.fromJson(): MODEL {
    return Gson().fromJson(this, MODEL::class.java)
}

fun <T> typeOfList(): Type {
    return object : TypeToken<List<T>>() {}.type
}

fun <T> String.getList(): ArrayList<T>? {
    val gson = GsonBuilder().create()
    return gson.fromJson(this, typeOfList<T>())
}*/

fun Int.toPx(): Int {
    val displayMetrics = Resources.getSystem().displayMetrics
    return (this * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
}

fun Int.toDp(): Int {
    val displayMetrics = Resources.getSystem().displayMetrics
    return (this / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
}

fun Float.toPx(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )
}


fun View.setHeight(height: Int) {
    val p = layoutParams as ViewGroup.LayoutParams
    p.height = height
    requestLayout()
}

fun Long.toMoneyString(): String {
    if (this == 0L)
        return "رایگان"
    return DecimalFormat("#,###,###,###,###").format(this) + " تومان"
}

fun TextView.toMoneyDashString() {
    this.paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}

fun View.setMargins(left: Int, top: Int, right: Int, bottom: Int) {
    if (layoutParams is ViewGroup.MarginLayoutParams) {
        val p = layoutParams as ViewGroup.MarginLayoutParams
        p.setMargins(left, top, right, bottom)
        requestLayout()
    }
}

fun Long.toMoney(): String {
    return DecimalFormat("#,###,###,###,###").format(this)
}

fun Drawable.setColor(color: Int, context: Context): Drawable {

    DrawableCompat.setTint(this, context.resources.getColor(color))
    return this
}

fun ImageView.setDrawableColor(color: Int) {
    setColorFilter(resources.getColor(color), android.graphics.PorterDuff.Mode.SRC_IN)
}

fun TextView.setDrawableColor(color: Int) {
    compoundDrawables.filterNotNull().forEach {
        it.colorFilter =
            PorterDuffColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_IN)
    }
}

fun TabLayout.setCustomFont() {

    val vg = this.getChildAt(0) as ViewGroup
    val tabsCount = vg.childCount

    for (j in 0 until tabsCount) {
        val vgTab = vg.getChildAt(j) as ViewGroup

        val tabChildsCount = vgTab.childCount

        for (i in 0 until tabChildsCount) {
            val tabViewChild = vgTab.getChildAt(i)
            if (tabViewChild is TextView) {
                //Put your font in assests folder
                //assign name of the font here (Must be case sensitive)
                tabViewChild.typeface =
                    Typeface.createFromAsset(context.assets, "fonts/yekan_bakh_regular.ttf")
            }

        }


    }

}

fun Bitmap.toByteArray(): ByteArray? {
    val stream = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.JPEG, 100, stream)
    return stream.toByteArray()
}

fun Location.hasMock(): Boolean =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        this.isMock
    else
        this.isFromMockProvider


fun getScreenWidth() = Resources.getSystem().displayMetrics.widthPixels
fun getScreenHeight() = Resources.getSystem().displayMetrics.heightPixels