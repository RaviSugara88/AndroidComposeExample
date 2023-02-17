package com.sdd.saniproadvance.navigation

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sdd.saniproadvance.screen.DashboardScreen
import com.sdd.saniproadvance.screen.LoginScreen
import com.sdd.saniproadvance.screen.ReportScreen
import com.sdd.saniproadvance.screen.SplashScreen
import com.sdd.saniproadvance.utils.navigation.NavigationScreen
import com.sdd.saniproadvance.viewmodel.MainViewModel

/*
class Navigation {
}*/

@Composable
fun StartNavigation(context: Activity,mainViewModel: MainViewModel){
    val navController = rememberNavController()
    
    NavHost(navController = navController ,
        startDestination = NavigationScreen.SplashScreen.route){

        composable(NavigationScreen.SplashScreen.route)  {
            SplashScreen(navController,context)
        }

        composable(NavigationScreen.LoginScreen.route){
            LoginScreen(navController,context)
        }

        composable(NavigationScreen.DashboardScreen.route){
            /** Received data through Argument and pass next screen through constectore*/
            val data = it.arguments?.getString("data")
            DashboardScreen(navController,context,data?:"",mainViewModel)
        }

        composable(NavigationScreen.ReportScreen.route){
            ReportScreen(navController)
        }
    }
}
