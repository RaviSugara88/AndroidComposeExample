package com.sdd.saniproadvance.screen

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sdd.saniproadvance.R
import com.sdd.saniproadvance.utils.SimpleText
import com.sdd.saniproadvance.utils.dialog.AlertDialogComponent
import com.sdd.saniproadvance.utils.navigation.NavigationScreen
import com.sdd.saniproadvance.utils.navigation.view.CircleShapeImage
import com.sdd.saniproadvance.utils.navigation.view.ImageRainbowBorder
import com.sdd.saniproadvance.viewmodel.UserViewModel
import kotlinx.coroutines.launch


//@Preview
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun UserProfileView(
    navHostController: NavHostController,
    mainViewModel: UserViewModel = viewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val lifecycleOwner = LocalLifecycleOwner.current
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var dialogState by rememberSaveable { mutableStateOf(false) }
    // var email by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(key1 = "test") {
        mainViewModel.userDetail(1)
        mainViewModel.userDetailRes.observe(lifecycleOwner) { data ->
            if (data != null) {
                name = data.name
                email = data.email
                phone = data.phoneNo

            } else {
                // validateEmail = false
                // validateEmailError ="Invalided email"
            }

        }

    }


    if (dialogState)
        AlertDialogComponent { st ->
            if (st) {
                dialogState = false
                navHostController.navigate(NavigationScreen.LoginScreen.route) {
                    launchSingleTop = true
                    //inclusive = true
                    popUpTo(NavigationScreen.UserProfileView.route) {
                        inclusive = true
                    }
                }
            } else {
                dialogState = false
            }
        }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            ),
            shape = RectangleShape,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 20.dp
            )
        ) {

            Box(Modifier.fillMaxWidth()) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp, top = 16.dp)
                        .clickable {
                            navHostController.popBackStack()
                        },
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_logout_24),
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp, top = 16.dp)
                        .clickable {
                            scope.launch {
                                //  AlertDialogComponent()
                                dialogState = true
                            }
                            //  navHostController.navigate(Navi)
                        },
                )

            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    // .fillMaxHeight()
                    // .background(Color.Blue)
                    .padding(top = 90.dp),

                contentAlignment = Alignment.TopCenter
            ) {
                CircleShapeImage("")
            }


            SimpleText(des = "User name", text = name,
                textStyle =  TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    lineHeight = 20.sp
                ),
            )
            Spacer(modifier = Modifier.height(10.dp))
            SimpleText(des = "Email", text = email,  textStyle =  TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                lineHeight = 20.sp
            ),)
            Spacer(modifier = Modifier.height(10.dp))
            SimpleText(des = "Mobile No", text = phone,  textStyle =  TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                lineHeight = 20.sp
            ),)


        }


    }


}

