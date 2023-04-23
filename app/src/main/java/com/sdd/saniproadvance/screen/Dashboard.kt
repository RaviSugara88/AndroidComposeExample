package com.sdd.saniproadvance.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavHostController
import com.sdd.saniproadvance.TestActivity
import com.sdd.saniproadvance.retrofit.util.ApiState
import com.sdd.saniproadvance.utils.dummyData
import com.sdd.saniproadvance.utils.navigation.NavigationScreen
import com.sdd.saniproadvance.utils.navigation.view.*
import com.sdd.saniproadvance.viewmodel.MainViewModel
import com.sdd.saniproadvance.viewmodel.UserViewModel


@Composable
fun DashboardScreen(navHostController: NavHostController,context: Activity,data:String,mainViewModel: UserViewModel){
    /** Api call through Retrofit*/
    Column(Modifier.fillMaxWidth()) {
        CustomToolbar(title = "Dashboard", isBackButtonVisible = false) {

        }
        val lifecycleOwner = LocalLifecycleOwner.current

        DisposableEffect(key1 = lifecycleOwner ){
            val observer = LifecycleEventObserver{_,event ->

             //   if (event== Lifecycle.Event.ON_PAUSE){
                    mainViewModel.getPost()
             //   }
            }
            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }

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
fun GETData(mainViewModel: UserViewModel){
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

            Box(contentAlignment = Alignment.Center,modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                ){
                androidx.compose.material.CircularProgressIndicator()
            }

        }
        ApiState.Empty->{

        }
    }
}
