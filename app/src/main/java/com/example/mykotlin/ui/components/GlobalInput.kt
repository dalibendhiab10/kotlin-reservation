package com.example.mykotlin.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mykotlin.utils.InputType

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun GlobalInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: Int? = null,
    inputType: InputType = InputType.TEXT,
    modifier: Modifier = Modifier
) {
    val keyboardOptions = when(inputType) {
        InputType.EMAIL -> KeyboardOptions(keyboardType = KeyboardType.Email)
        InputType.PASSWORD -> KeyboardOptions(keyboardType = KeyboardType.Password)
        InputType.NUMBER -> KeyboardOptions(keyboardType = KeyboardType.Number)
        InputType.PHONE -> KeyboardOptions(keyboardType = KeyboardType.Phone)
        else -> KeyboardOptions(keyboardType = KeyboardType.Text)
    }

    val visualTransformation = if (inputType == InputType.PASSWORD) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        placeholder = {
            Text(
                text = placeholder,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF4CAF50).copy(alpha = 0.5f)
                )
            )
        },
        leadingIcon = icon?.let {
            {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(24.dp)
                )
            }
        },
        shape = RoundedCornerShape(50.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFF4CAF50),
            unfocusedTextColor = Color(0xFF4CAF50),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color(0xFF4CAF50),
            unfocusedIndicatorColor = Color(0xFF4CAF50),
            cursorColor = Color(0xFF4CAF50)
        ),
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = Color(0xFF4CAF50)
        ),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation
    )
}
