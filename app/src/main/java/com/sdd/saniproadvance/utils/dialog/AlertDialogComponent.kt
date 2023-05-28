package com.sdd.saniproadvance.utils.dialog

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.sdd.saniproadvance.R


/*@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
   // GFGAppTheme {
        AlertDialogComponent();
  //  }
}*/

@Composable
fun AlertDialogComponent(event:(status:Boolean)->Unit) {
    // below line is use to get
    // the context for our app.
    val context = LocalContext.current

    // below line is use to set our state
    // of dialog box to open as true.
    val openDialog = remember { mutableStateOf(true) }

    // below line is to check if the
    // dialog box is open or not.
    if (openDialog.value) {
        // below line is use to
        // display a alert dialog.
        AlertDialog(
            // on dialog dismiss we are setting
            // our dialog value to false.
            onDismissRequest = { openDialog.value = false },

            // below line is use to display title of our dialog
            // box and we are setting text color to white.
            title = { Text(text = "Logout", color = Color.White) },

            // below line is use to display
            // description to our alert dialog.
            text = { Text("Are you sure to logout?", color = Color.White,) },

            // in below line we are displaying
            // our confirm button.
            confirmButton = {
                // below line we are adding on click
                // listener for our confirm button.
                TextButton(
                    onClick = {
                        openDialog.value = false
                        event(true)
                       // Toast.makeText(context, "Confirm Button Click", Toast.LENGTH_LONG).show()
                    }
                ) {
                    // in this line we are adding
                    // text for our confirm button.
                    Text("Yes", color = Color.White)
                }
            },
            // in below line we are displaying
            // our dismiss button.
            dismissButton = {
                // in below line we are displaying
                // our text button
                TextButton(
                    // adding on click listener for this button
                    onClick = {
                        openDialog.value = false
                        event(false)
                       // Toast.makeText(context, "Dismiss Button Click", Toast.LENGTH_LONG).show()
                    }
                ) {
                    // adding text to our button.
                    Text("No", color = Color.White)
                }
            },
            // below line is use to add background color to our alert dialog
            backgroundColor = colorResource(id = R.color.teal_200),

            // below line is use to add content color for our alert dialog.
            contentColor = Color.White
        )
    }
}

