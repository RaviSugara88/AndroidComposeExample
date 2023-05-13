package com.sdd.saniproadvance.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sdd.saniproadvance.screen.*
import com.sdd.saniproadvance.utils.navigation.NavigationScreen
import com.sdd.saniproadvance.viewmodel.UserViewModel

/*
class Navigation {
}*/

@Composable
fun StartNavigation(context: Activity,mainViewModel: UserViewModel){
    val navController = rememberNavController()
    
    NavHost(navController = navController ,
        startDestination = NavigationScreen.SplashScreen.route){

        composable(NavigationScreen.SplashScreen.route)  {
            SplashScreen(navController,context)
        }

        composable(NavigationScreen.LoginScreen.route){
            LoginScreen(navController,context,mainViewModel)
        }

        composable(NavigationScreen.DashboardScreen.route){
            /** Received data through Argument and pass next screen through constectore*/
            val data = it.arguments?.getString("data")
            DashboardScreen(navController,context,data?:"",mainViewModel)
        }

        composable(NavigationScreen.ReportScreen.route){
            ReportScreen(navController)
        }

        composable(NavigationScreen.UserRegistrationScreen.route){
            UserRegistration(navController,context,mainViewModel)
        }

        composable(NavigationScreen.UserProfileView.route){
            UserProfileView(navController,mainViewModel)
        }
    }
}
