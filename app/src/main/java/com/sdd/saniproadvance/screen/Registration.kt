package com.sdd.saniproadvance.screen

import android.app.Activity
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sdd.saniproadvance.R
import com.sdd.saniproadvance.room_db.model.UserData
import com.sdd.saniproadvance.utils.CustomOutlinedTextField
import com.sdd.saniproadvance.utils.HeadingText
import com.sdd.saniproadvance.utils.appIcon
import com.sdd.saniproadvance.utils.navigation.NavigationScreen
import com.sdd.saniproadvance.utils.navigation.view.ButtonWithCutCornerShape
import com.sdd.saniproadvance.utils.navigation.view.CustomToolbar
import com.sdd.saniproadvance.utils.navigation.view.MarginsToTop
import com.sdd.saniproadvance.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


//@Preview
@Composable

fun UserRegistration(
    navHostController: NavHostController,
    context: Activity,
    mainViewModel: UserViewModel
){


    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    val screenHeight = configuration.screenHeightDp.dp
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phoneNo by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("") }
    var gender by rememberSaveable { mutableStateOf("") }
    var dob by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var validateName by rememberSaveable { mutableStateOf(true) }
    var validateEmail by rememberSaveable { mutableStateOf(true) }
    var validatePhoneNo by rememberSaveable { mutableStateOf(true) }
    var validateAddress by rememberSaveable { mutableStateOf(true) }
  //  var validatgender by rememberSaveable { mutableStateOf(true) }
   // var validatdob by rememberSaveable { mutableStateOf(true) }
    var validatePassword by rememberSaveable { mutableStateOf(true) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false)}

    val validateNameError = "Please, input a valid name"
    val validateEmailError ="The format of the email doesn't seem right"
    val validatePhoneNoError ="The format of the phone number doesn't seem right"
    val validatePasswordError ="Must mix capital and non-capital letters, a number, special character and a minimum length of 8"

    fun validateData(name:String,email:String,phoneNo:String,password:String):Boolean{
        val passwordRegex = "(?=.*[@#\$%^&+=])".toRegex()//"(?=.*\\d)(?=.*[a-z](?=.*[@#$%^&*+=]).{8,})".toRegex()
        validateName = name.isNotBlank()
        validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        validatePhoneNo =Patterns.PHONE.matcher(phoneNo).matches()
       // validatePassword = passwordRegex.matches(password)

        return validateName && validateEmail && validatePhoneNo
    }

    fun register(
        name:String,
        email:String,
        phone:String,
        password:String,
        address:String
    ){
        if (validateData(name,email,phone,password)){

            mainViewModel.addUser(UserData(name=name, email = email, phoneNo = phoneNo, password = password, address = address))
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(context,"User register successfully ",Toast.LENGTH_LONG).show()
                delay(2000)
                navigateToLogin(navHostController)
            }



/*
            navHostController.navigate(NavigationScreen.DashboardScreen.createRoute("hello this is Dashboard Screen")){
                */
/**  launchSingleTop =true
                 * create only on instance in BackStack
                like A-B-C-A-B = A-B-C
                 *//*

                launchSingleTop = true
            }
*/

            //print toast
        }else{
            //print toast
        }
    }



    /* @Composable
fun MyScaffold(@StringRes titleId: Int, upAvailable: Boolean, onUpClicked: () -> Unit, content: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { MyText(textId = titleId) }, backgroundColor = Color.Black, navigationIcon = {
                if (upAvailable) {
                    IconButton(onClick = { onUpClicked() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                }
            })
        },
        backgroundColor = Color.Transparent,
        content = content
    )
}*/

    Surface() {
        val materialBlue700= Color(0xFF1976D2)
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))

        Scaffold(
/*            topBar = { TopAppBar(title = {Text("Registration")},backgroundColor = materialBlue700, navigationIcon = {
                IconButton(onClick = {  }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            })  }*/

        ) { contentPadding ->
            // Screen content
           // Box(modifier = Modifier.padding(contentPadding)) { /* ... */ }

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
                .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                CustomToolbar(title = "Registration") {
                    navHostController.popBackStack()
                }

                MarginsToTop(screenHeight = (screenHeight*5/100))
                appIcon()
                MarginsToTop(screenHeight = (screenHeight*2/100))
                HeadingText(text = stringResource(R.string.enter_your_detail))
                //  SimpleText(text = stringResource(R.string.info_otp))
                MarginsToTop(screenHeight = 16.dp)

                CustomOutlinedTextField(
                    value = name,
                    onValueChange = {name=it},
                    label = "Name",
                    showError = !validateName,
                    errorMessage = validateNameError,
                    leadingIconImageVector = Icons.Default.Person,
                    leadingIconDescription = "na",
                    keyBoardOption = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {focusManager.moveFocus(FocusDirection.Down)}
                    )
                )
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
                CustomOutlinedTextField(
                    value = phoneNo,
                    onValueChange = {phoneNo=it},
                    textLength = 10,
                    label = "Phone Number",
                    showError = !validatePhoneNo,
                    errorMessage = validatePhoneNoError,
                    leadingIconImageVector = Icons.Default.Phone,
                    leadingIconDescription = "na",
                    keyBoardOption = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {focusManager.moveFocus(FocusDirection.Down)}
                    ),
                )
                CustomOutlinedTextField(
                    value = address,
                    onValueChange = {address=it},
                    label = "Address",
                    leadingIconImageVector = Icons.Default.Home,
                    leadingIconDescription = "na",
                    keyBoardOption = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        // onNext = {focusManager.moveFocus(FocusDirection.Down)}
                    ),
                )
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

                MarginsToTop(screenHeight = 10.dp)
                ButtonWithCutCornerShape(stringResource(id = R.string.register)){
                    register(name,email,phoneNo,password,address)
                }

                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 28.dp)
                        .clickable {
                                   navigateToLogin(navHostController)


                        },text = "Login", style = TextStyle(color = Color.Blue, fontSize = 14.sp),
                        textDecoration = TextDecoration.Underline)
                }




            }

        }


    }



}

fun navigateToLogin(navHostController: NavHostController) {
    navHostController.navigate(NavigationScreen.LoginScreen.route) {
        /**  launchSingleTop =true
         * create only on instance in BackStack
        like A-B-C-A-B = A-B-C
         */
        /**  launchSingleTop =true
         * create only on instance in BackStack
        like A-B-C-A-B = A-B-C
         */

        launchSingleTop = true
    }

}
