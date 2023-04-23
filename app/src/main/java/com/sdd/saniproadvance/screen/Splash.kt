package com.sdd.saniproadvance.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sdd.saniproadvance.R
import com.sdd.saniproadvance.utils.appIcon
import com.sdd.saniproadvance.utils.hideSystemUI
import com.sdd.saniproadvance.utils.navigation.NavigationScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navHostController: NavHostController,context: Activity){
   // context.hideSystemUI()
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {
        appIcon()
        RememberUpdatedState {
            navHostController.navigate(NavigationScreen.LoginScreen.route){
                /** popUpTo(NavigationScreen.LoginScreen.route)
                 * remove all back stack and navigate to target Screen like Login
                 * [inclusive = true] remove all back stack and close Activity and Add Screen name
                 * like this we need to remove Splash Screen back stack
                 * if we need to clear all back stack apart form current stack that time
                wee need to add inclusive = false
                 * */

                /** popUpTo(NavigationScreen.LoginScreen.route)
                 * remove all back stack and navigate to target Screen like Login
                 * [inclusive = true] remove all back stack and close Activity and Add Screen name
                 * like this we need to remove Splash Screen back stack
                 * if we need to clear all back stack apart form current stack that time
                wee need to add inclusive = false
                 * */

                popUpTo(NavigationScreen.SplashScreen.route){
                    inclusive = true
                }
            }

        }

//        val coroutineScope = rememberCoroutineScope()
//        coroutineScope.launch {
//            delay(2000)
//
//        }
    }

}


@Composable
fun RememberUpdatedState(
    onTimeout:()->Unit
){
    val updateOnTimeOut by rememberUpdatedState(newValue = onTimeout)

    LaunchedEffect( true ){
        delay(2000L)
        updateOnTimeOut()
    }

}