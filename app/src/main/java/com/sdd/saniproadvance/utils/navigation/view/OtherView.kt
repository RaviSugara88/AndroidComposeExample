package com.sdd.saniproadvance.utils.navigation.view

import android.print.PrintAttributes.Margins
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sdd.saniproadvance.retrofit.Post
import com.sdd.saniproadvance.utils.User


@Composable
fun MarginsToTop(screenHeight:Dp){
    Spacer(modifier = Modifier.height(screenHeight))
}

@Composable
fun EachRow(todo: Post){
    /**item view */
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp)

        ) {
            ClipRoundedCorner()
            Text(text = todo.body,fontStyle = FontStyle.Italic,modifier=Modifier.padding(vertical = 5.dp, horizontal = 5.dp))
        }
    }
}

@Composable
fun Recyclerview(user:List<Post>){
    LazyColumn{
        items(user){use->
            EachRow(todo = use )
        }
    }
}