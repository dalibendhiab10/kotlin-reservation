package com.example.mykotlin.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun GlobalButton(
    onClick: () -> Unit,
    buttonText: String,
    isLoading: Boolean,
    modifier: Modifier = Modifier

) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),

        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xff2e7d32), // Background color
            contentColor = Color.White         // Text/icon color
        ),

    ) {

        if (isLoading) {
            // Show loading GIF

            AsyncImage(
                model = "https://media.tenor.com/tga0EoNOH-8AAAAM/loading-load.gif", // Put the URL of your GIF here
                contentDescription = "Loading",
                modifier = Modifier.size(40.dp)
            )
        } else {
            Text(text = buttonText)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGlobalButton() {
    GlobalButton(
        onClick = {},
        buttonText = "Login",
        isLoading = false
    )
}
