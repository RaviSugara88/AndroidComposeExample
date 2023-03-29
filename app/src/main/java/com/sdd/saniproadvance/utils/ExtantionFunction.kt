package com.sdd.saniproadvance.utils

import android.app.Activity
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.util.Log
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

fun Activity.checkNetWork(){
    val connectivityManager = getSystemService(ConnectivityManager::class.java)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback(){
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                Log.d("NETWORK_STATE", "available")

            }

            override fun onLost(network: Network) {
                super.onLost(network)
                Log.d("NETWORK_STATE", "lost")

            }
        })
    }
}