package com.sdd.saniproadvance.utils.navigation.view

import android.service.autofill.OnClickAction
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sdd.saniproadvance.ui.theme.Purple40
import com.sdd.saniproadvance.ui.theme.Purple80

//@Preview
@Composable
fun CustomToolbar(title:String,isBackButtonVisible:Boolean=true,onClickAction: ()->Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(45.dp)
        .background(color = Purple80)) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 0.dp)) {
            if (isBackButtonVisible){
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 10.dp)
                        .clickable {onClickAction() },
                )
            }
            Text(
                text = title, style = TextStyle(fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif),
                modifier = Modifier
                    .align(Alignment.Center)
            )

        }

    }
}