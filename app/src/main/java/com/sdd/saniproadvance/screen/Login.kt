package com.sdd.saniproadvance.screen

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sdd.saniproadvance.R
import com.sdd.saniproadvance.utils.HeadingText
import com.sdd.saniproadvance.utils.SimpleOutlinedTextFieldSample
import com.sdd.saniproadvance.utils.SimpleText
import com.sdd.saniproadvance.utils.appIcon
import com.sdd.saniproadvance.utils.navigation.NavigationScreen
import com.sdd.saniproadvance.utils.navigation.view.ButtonWithCutCornerShape
import com.sdd.saniproadvance.utils.navigation.view.MarginsToTop

@Composable
fun LoginScreen(navHostController: NavHostController,context: Activity){
    val configuration = LocalConfiguration.current
    var mobileNo by remember { mutableStateOf("") }
    val screenHeight = configuration.screenHeightDp.dp
   // val screenWidth = configuration.screenWidthDp.dp
   // val hh = c
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
       // Spacer(modifier = Modifier.height((screenHeight*10/100)))//height: Dp
        MarginsToTop(screenHeight = (screenHeight*10/100))
        appIcon()
        MarginsToTop(screenHeight = (screenHeight*5/100))
        HeadingText(text = stringResource(R.string.enter_your_phone_number))
        SimpleText(text = stringResource(R.string.info_otp))
        MarginsToTop(screenHeight = (screenHeight*5/100))
        SimpleOutlinedTextFieldSample{
            mobileNo = it
        }

        MarginsToTop(screenHeight = (screenHeight*5/100))
        ButtonWithCutCornerShape(stringResource(id = R.string.send)){
            Toast.makeText(context,mobileNo,Toast.LENGTH_LONG).show()
            navHostController.navigate(NavigationScreen.DashboardScreen.createRoute("hello this is Dashboard Screen")){
                /**  launchSingleTop =true
                 * create only on instance in BackStack
                like A-B-C-A-B = A-B-C
                 */
                launchSingleTop = true
            }

        }
        

        /**
         * when we need to call activity through Fragment
        Button(onClick = {
        val intent = Intent(context,TestActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        }) {
        Text(text = "Go to Un other Activity")
        }
         */
        //  context.showSystemUI()
    }

}