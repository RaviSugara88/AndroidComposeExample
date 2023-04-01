package com.sdd.saniproadvance.screen

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sdd.saniproadvance.R
import com.sdd.saniproadvance.utils.*
import com.sdd.saniproadvance.utils.navigation.NavigationScreen
import com.sdd.saniproadvance.utils.navigation.view.ButtonWithCutCornerShape
import com.sdd.saniproadvance.utils.navigation.view.MarginsToTop


@Composable
fun LoginScreen(navHostController: NavHostController,context: Activity){
    val configuration = LocalConfiguration.current
    var mobileNo by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    val screenHeight = configuration.screenHeightDp.dp
    val context = LocalContext.current
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
      //  SimpleText(text = stringResource(R.string.info_otp))
        MarginsToTop(screenHeight = 16.dp)
/*
        SimpleOutlinedTextFieldSample{
            mobileNo = it
        }
*/
        Spacer(modifier = Modifier.height(5.dp))
        CustomOutlinedTextField(
            value = password,
            onValueChange ={password = it} ,
            label = "User Name",
            leadingIconImageVector =Icons.Default.Person ,
            leadingIconDescription ="dfdssdf" ,
            onVisibilityChange ={} ,
            isPasswordField = false,
            isSingleLine = true

        )
        MarginsToTop(screenHeight = 10.dp)
        CustomOutlinedTextField(
            value = password,
            onValueChange ={password = it} ,
            label = "Password",
            leadingIconImageVector =Icons.Default.Lock ,
            leadingIconDescription ="dfdssdf" ,
            onVisibilityChange ={} ,
            isPasswordField = true,
            isSingleLine = true
        )

       MarginsToTop(10.dp)

        Box(modifier = Modifier.fillMaxWidth()) {
            Text(modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 28.dp)
                .clickable {
                    Toast
                        .makeText(context, "Jai Mata di", Toast.LENGTH_LONG)
                        .show()
                },text = "Register User", style = TextStyle(color = Color.Blue, fontSize = 14.sp),
                textDecoration = TextDecoration.Underline)
        }


        MarginsToTop(screenHeight = 10.dp)
        ButtonWithCutCornerShape(stringResource(id = R.string.login)){
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