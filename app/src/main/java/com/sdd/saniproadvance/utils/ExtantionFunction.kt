package com.sdd.saniproadvance.utils

import android.app.Activity
import android.os.Build
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.core.view.WindowCompat

fun Activity.hideSystemUI() {

    //Hides the ugly action bar at the top
    actionBar?.hide()

    //Hide the status bars

    WindowCompat.setDecorFitsSystemWindows(window, false)

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    } else {
        window.insetsController?.apply {
            hide(WindowInsets.Type.statusBars())
            systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}
fun Activity.showSystemUI() {

    //Hides the ugly action bar at the top
    actionBar?.show()

    //Hide the status bars

    WindowCompat.setDecorFitsSystemWindows(window, true)

   // if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
 //   } else {
/*
        window.insetsController?.apply {
            hide(WindowInsets.Type.statusBars())
            systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
*/
   // }
}