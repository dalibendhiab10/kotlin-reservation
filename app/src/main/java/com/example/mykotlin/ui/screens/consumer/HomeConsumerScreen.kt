package com.example.mykotlin.ui.screens.consumer

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mykotlin.ui.components.EventCard
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.example.mykotlin.api.ApiService
import com.example.mykotlin.models.EstablishmentData
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

@Composable
fun HomeConsumerScreen(navController: NavHostController) {
    val establishments = remember { mutableStateOf<List<EstablishmentData>>(emptyList()) }
    val isLoading = remember { mutableStateOf(true) }
    val errorMessage = remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val response = ApiService.api?.getEstablishments()
                if (response != null) {
                    establishments.value = response
                }
                isLoading.value = false
            } catch (e: Exception) {
                errorMessage.value = "Error: ${e.message}"
                isLoading.value = false
            }
        }
    }

    if (isLoading.value) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (errorMessage.value != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = errorMessage.value ?: "Unknown error")
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(establishments.value) { establishment ->
                EventCard(
                    id = establishment.id,
                    title = establishment.name,
                    place = establishment.type,
                    date = establishment.location,
                    imageUrl = establishment.logoUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            // Pass only the id when navigating
                            navController.navigate("establishmentDetail/${establishment.id}")
                        }
                )
            }
        }
    }
}
