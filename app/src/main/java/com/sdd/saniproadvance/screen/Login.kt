package com.sdd.saniproadvance.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sdd.saniproadvance.R
import com.sdd.saniproadvance.utils.*
import com.sdd.saniproadvance.utils.navigation.NavigationScreen
import com.sdd.saniproadvance.utils.navigation.view.ButtonWithCutCornerShape
import com.sdd.saniproadvance.utils.navigation.view.MarginsToTop
import com.sdd.saniproadvance.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoginScreen(
    navHostController: NavHostController,
    context2: Activity,
    mainViewModel: UserViewModel = viewModel()
){
    val configuration = LocalConfiguration.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    val screenHeight = configuration.screenHeightDp.dp
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current


    var validatePassword by rememberSaveable { mutableStateOf(true) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false)}

    var validateEmailError by rememberSaveable { mutableStateOf("The format of the email doesn't seem right") }

    var validatePasswordError by rememberSaveable { mutableStateOf("Enter password") }
    var validateEmail by rememberSaveable { mutableStateOf(true) }


    DisposableEffect(key1 = lifecycleOwner ){
        val observer = LifecycleEventObserver{_,event ->

          /** Wee can used Android lifecycle in side [DisposableEffect]
          if (event==Lifecycle.Event.ON_CREATE){
                println("ON_CREATE")
            }*/


            mainViewModel.userLoginRes.observe(lifecycleOwner){data->
                if (data!=null){
                    validateEmail = true
                    validatePassword = true

                    if (data?.password.equals(password,true)){
                        navigateToDashboardScreen(navHostController)
                    }else{
                        validatePassword = false
                        validatePasswordError = "Invalided password"
                    }

                }else{
                    validateEmail = false
                    validateEmailError ="Invalided email"
                }

            }

        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }

    }
    fun validateData(email: String, password: String):Boolean{
        val passwordRegex = "(?=.*[@#\$%^&+=])".toRegex()//"(?=.*\\d)(?=.*[a-z](?=.*[@#$%^&*+=]).{8,})".toRegex()
        // validateName = name.isNotBlank()
        validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        return if (email.isEmpty()){
            validateEmail = false
            validateEmailError = "Please enter email"
            false
        }else if (!validateEmail){
            validateEmail = false
            validateEmailError = "The format of the email doesn't seem right"
            false
        }else if (password.isEmpty()){
            validatePassword = false
            validatePasswordError = "Please enter password"
            false

        }else{
            true
        }
        // validatePhoneNo = Patterns.PHONE.matcher(phoneNo).matches()
        // validatePassword = passwordRegex.matches(password)

      //  return validateEmail
    }

    /** also collect data like this*/
   // val gameUiState by mainViewModel.userLoginRes.collectAsState()


    //    val searchResults by viewModel.searchResults.observeAsState(listOf())
    //

 //   val data by mainViewModel.userLoginRes.observeAsState()




    // val screenWidth = configuration.screenWidthDp.dp
   // val hh = c

    //2	2	Pawan	pawan@yopmail.com	12345678	5869365214	thff
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
            value = email,
            onValueChange = {email=it},
            label = "Email",
            showError = !validateEmail,
            errorMessage = validateEmailError,
            leadingIconImageVector = Icons.Default.Email,
            leadingIconDescription = "na",
            keyBoardOption = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(FocusDirection.Down)}
            )
        )
        MarginsToTop(screenHeight = 10.dp)
        CustomOutlinedTextField(
            value = password,
            onValueChange = {password=it},
            label = "Password",
            leadingIconImageVector = Icons.Default.Lock,
            leadingIconDescription = "na",
            showError = !validatePassword,
            errorMessage = validatePasswordError,
            isPasswordField = true,
            isPasswordVisible = isPasswordVisible,
            onVisibilityChange ={isPasswordVisible=it},
            keyBoardOption = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {focusManager.clearFocus()}
            ),

            )

       MarginsToTop(10.dp)

        Box(modifier = Modifier.fillMaxWidth()) {
            Text(modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 28.dp)
                .clickable {

                    navHostController.navigate(NavigationScreen.UserRegistrationScreen.route) {
                        /**  launchSingleTop =true
                         * create only on instance in BackStack
                        like A-B-C-A-B = A-B-C
                         */
                        // launchSingleTop = true
                    }

/*
                    Toast
                        .makeText(context, "Jai Mata di", Toast.LENGTH_LONG)
                        .show()
*/
                },text = "Register", style = TextStyle(color = Color.Blue, fontSize = 14.sp),
                textDecoration = TextDecoration.Underline)
        }


        MarginsToTop(screenHeight = 10.dp)
        ButtonWithCutCornerShape(stringResource(id = R.string.login)){
            if (validateData(email,password)){
                CoroutineScope(Dispatchers.Main).launch {
                    mainViewModel.userLogin(email)
                 //   val data =   mainViewModel.userRepository.loginUser(email)
/*
                    if (data!=null){
                        validateEmail = true
                        validatePassword = true

                        if (data?.password.equals(password,true)){
                            navigateToDashboardScreen(navHostController)
                        }else{
                            validatePassword = false
                            validatePasswordError = "Invalided password"
                        }

                    }else{
                        validateEmail = false
                        validateEmailError ="Invalided email"
                    }
*/

                   // Log.e("TAG", "LoginScreen: ${data?.name}" )
                }

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

 fun navigateToDashboardScreen(navHostController: NavHostController) {
    navHostController.navigate(NavigationScreen.DashboardScreen.createRoute("hello this is Dashboard Screen")){
        launchSingleTop =true
    }

}
