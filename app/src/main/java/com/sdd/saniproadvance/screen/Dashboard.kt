package com.sdd.saniproadvance.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.sdd.saniproadvance.TestActivity
import com.sdd.saniproadvance.retrofit.util.ApiState
import com.sdd.saniproadvance.utils.dummyData
import com.sdd.saniproadvance.utils.navigation.NavigationScreen
import com.sdd.saniproadvance.utils.navigation.view.*
import com.sdd.saniproadvance.viewmodel.MainViewModel


@Composable
fun DashboardScreen(navHostController: NavHostController,context: Activity,data:String,mainViewModel: MainViewModel){
    /** Api call through Retrofit*/
    Column(Modifier.fillMaxWidth()) {
        CustomToolbar(title = "Dashboard", isBackButtonVisible = false) {

        }
        GETData(mainViewModel = mainViewModel)
    }


/*
    Button(onClick = {
       val intent = Intent(context,TestActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }) {
        Text(text = "Go to Un other Activity")
    }
*/
  //  CustomPainterUsage()
  //  CustomPainterModifier()


    //ClipRoundedCorner()
/*
    Button(onClick = {
        navHostController.navigate(NavigationScreen.ReportScreen.route){
            */
/** popUpTo(NavigationScreen.LoginScreen.route)
             * remove all back stack and navigate to target Screen like Login
             * Skip Login Screen *//*

            popUpTo(NavigationScreen.LoginScreen.route)
        }

    }) {
        Text(text = data)
    }
*/





}
//alt+1 =Project stacture

@Composable
fun GETData(mainViewModel: MainViewModel){
    when(val result = mainViewModel.response.value){
        is ApiState.Success->{
            LazyColumn{
                items(result.data){ response->
                    EachRow( response)
                }
            }
        }
        is ApiState.Failure->{
            androidx.compose.material.Text(text = "${result.msg}")
        }
        ApiState.Loading->{
            androidx.compose.material.CircularProgressIndicator()
        }
        ApiState.Empty->{

        }
    }
}
