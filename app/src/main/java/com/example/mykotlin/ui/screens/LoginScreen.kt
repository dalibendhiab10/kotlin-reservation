package com.example.mykotlin.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mykotlin.api.ApiService
import com.example.mykotlin.models.LoginRequest
import com.example.mykotlin.utils.Constants
import kotlinx.coroutines.launch
import com.example.mykotlin.ui.components.GlobalButton
import androidx.compose.ui.platform.LocalContext
import com.example.mykotlin.R
import com.example.mykotlin.ui.components.GlobalInput
import com.example.mykotlin.utils.InputType

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current  // Get the context

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        GlobalInput(
            value = email,
            onValueChange = { email = it },
            placeholder = "E-mail ID",
            icon = R.drawable.ic_email,
            inputType = InputType.EMAIL
        )
        Spacer(modifier = Modifier.height(8.dp))

        GlobalInput(
            value = password,
            onValueChange = { password = it },
            placeholder = "Password",
            icon = R.drawable.ic_email,
            inputType = InputType.PASSWORD
        )
        Spacer(modifier = Modifier.height(16.dp))

        GlobalButton(
            onClick = {
                scope.launch {
                    isLoading = true
                    try {
                        // Ensure ApiService is initialized before using it
                        ApiService.initialize(context.applicationContext) // Initialize ApiService

                        val response = ApiService.getApiInterface().login(LoginRequest(email, password))

                        Constants.saveToken(context, response.token)
                        Constants.saveRole(context, response.role)
                        navController.navigate("homeConsumer")
                    } catch (e: Exception) {
                        errorMessage = "Login failed: ${e.localizedMessage}"
                    }
                    isLoading = false
                }
            },
            buttonText = "Login",
            isLoading = isLoading
        )

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate("register") }) {
            Text("Don't have an account? Register")
        }

        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}

