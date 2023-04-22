package com.sdd.saniproadvance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sdd.saniproadvance.navigation.StartNavigation
import com.sdd.saniproadvance.ui.theme.SaniProAdvanceTheme
import com.sdd.saniproadvance.viewmodel.MainViewModel
import com.sdd.saniproadvance.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

//https://www.youtube.com/watch?v=o3omhZspWJw
@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    private val mainViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaniProAdvanceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StartNavigation(this,mainViewModel)

                    mainViewModel.userDetailRes.observe(this){

                    }
                 //   Greeting("Android")
                }
            }
        }
    }
}
//@Preview(showBackground = true, name = "dashbord", showSystemUi = true, widthDp = 250, heightDp = 250)