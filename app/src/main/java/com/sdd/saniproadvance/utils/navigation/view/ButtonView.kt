package com.sdd.saniproadvance.utils.navigation.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sdd.saniproadvance.R
import com.sdd.saniproadvance.utils.ButtonText


@Composable
fun SimpleButton() {
    Button(onClick = {
        //your onclick code here
    }) {
        Text("Hello")
    }
}

@Composable
fun ButtonWithColor() {
    Button(
        onClick = {
            //your onclick code
        },
        colors = ButtonDefaults.buttonColors(Color.DarkGray)
    )

    {
        Text(text = "Button with gray background", color = Color.White)
    }
}

@Composable
fun ButtonWithTwoTextView() {
    Button(onClick = {
        //your onclick code here
    }) {
        Text(text = "Click ", color = Color.Magenta)
        Text(text = "Here", color = Color.Green)
    }

}

@Composable
fun ButtonWithIcon() {
    Button(onClick = {}) {
        Image(
            painterResource(id = R.drawable.logo),
            contentDescription = "Cart button icon",
            modifier = Modifier.size(20.dp)
        )

        Text(text = "Add to cart", Modifier.padding(start = 10.dp))
    }
}

@Composable
fun ButtonWithRectangleShape() {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(Color.Green),
        shape = RectangleShape
    ) {
        Text(text = stringResource(id = R.string.send))
    }
}

@Composable
fun ButtonWithRoundCornerShape() {
    Button(onClick = {}, shape = RoundedCornerShape(20.dp)) {
        Text(text = "Round corner shape")
    }
}

@Composable
fun ButtonWithCutCornerShape(text: String, block: () -> Unit) {
    //CutCornerShape(percent: Int)- it will consider as percentage
    //CutCornerShape(size: Dp)- you can pass Dp also.
    //Here we use Int, so it will take percentage.
    Button(
        onClick = {
            block()
        },
        colors = ButtonDefaults.outlinedButtonColors(colorResource(id = R.color.green)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 26.dp, end = 26.dp),
        shape = CutCornerShape(5)
    ) {
        ButtonText(text = text)
    }
}

@Composable
fun ButtonWithBorder() {
    Button(
        onClick = {
            //your onclick code
        },
        border = BorderStroke(1.dp, Color.Red),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
    ) {
        Text(text = "Button with border", color = Color.DarkGray)
    }
}

@Composable
fun ButtonWithElevation() {
    Button(
        onClick = {
            //your onclick code here
        }, elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        )
    ) {
        Text(text = "Button with elevation")
    }
}