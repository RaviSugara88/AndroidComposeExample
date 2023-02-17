package com.sdd.saniproadvance.utils.navigation
/** For add unik tage name  Tag name All screen
 * create Route*/
sealed class NavigationScreen(val route:String){
    object SplashScreen : NavigationScreen("splashscreen")
    object LoginScreen : NavigationScreen("loginscreen")
    object DashboardScreen : NavigationScreen("{data}/dashboard"){
        /**if we need to pass data one Screen to another Screen*/
        fun createRoute(data:String) = " $data/dashboard"
    }
    object ReportScreen : NavigationScreen("reportscreen")

}
