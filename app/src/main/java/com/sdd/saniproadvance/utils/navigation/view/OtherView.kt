package com.sdd.saniproadvance.utils.navigation.view

import android.print.PrintAttributes.Margins
import android.widget.Toast
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sdd.saniproadvance.retrofit.Post
import com.sdd.saniproadvance.ui.theme.avenirBold
import com.sdd.saniproadvance.utils.User


@Composable
fun MarginsToTop(screenHeight:Dp){
    Spacer(modifier = Modifier.height(screenHeight))
}

@Composable
fun EachRow(todo: Post){
    /**item view */
    /** Add Expanded view */
    val expanded = remember { mutableStateOf(false) }

    val extraPadding by animateDpAsState(
        if (expanded.value) 24.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    //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

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable(onClick = {
                Toast
                    .makeText(context, "hey ${todo.body}", Toast.LENGTH_SHORT)
                    .show()
            }),
        shape = RoundedCornerShape(4.dp),

    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.padding(10.dp)

            ) {
                ClipRoundedCorner()
                Text(text = todo.body,fontStyle = FontStyle.Italic, maxLines = 2, fontFamily = avenirBold,modifier= Modifier
                    .padding(vertical = 5.dp, horizontal = 5.dp)
                    .weight(1f))

                OutlinedButton(onClick = { expanded.value = !expanded.value}) {
                //    Text(text = "Show More")
                    Text(if (expanded.value) "Show less" else "Show more")

                }

            }

            if (expanded.value){

                Column(modifier = Modifier.padding(
                    bottom = extraPadding.coerceAtLeast(0.dp)
                )) {
                    Text(text =todo.body, maxLines = 4)
                }

            }

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