package com.dua.ynabapplication.utils.extension

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.view.WindowManager
import androidx.fragment.app.Fragment as SupportFragment

fun Activity.dpToPx(dp: Int) = (dp * Resources.getSystem().displayMetrics.density).toInt()

fun Activity.getScreenHeight(): Int{
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.y
}

fun Activity.getScreenWidth(): Int{
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.x
}

fun SupportFragment.getScreenWidth(): Int{
    return requireActivity().getScreenWidth()
}

fun SupportFragment.getScreenHeight(): Int{
    return requireActivity().getScreenHeight()
}