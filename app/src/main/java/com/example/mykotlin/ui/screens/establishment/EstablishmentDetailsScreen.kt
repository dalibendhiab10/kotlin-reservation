package com.example.mykotlin.ui.screens.establishment

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.mykotlin.api.ApiService
import com.example.mykotlin.models.EstablishmentData
import com.example.mykotlin.ui.components.EventCard
import com.example.mykotlin.ui.components.GlobalButton
import kotlinx.coroutines.launch
import retrofit2.HttpException

@Composable
fun EstablishmentDetailScreen(establishmentId: Int) {
    val establishmentData = remember { mutableStateOf<EstablishmentData?>(null) }
    val isLoading = remember { mutableStateOf(true) }
    val errorMessage = remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(establishmentId) {
        coroutineScope.launch {
            try {
                val response = ApiService.api?.getEstablishmentById(establishmentId)
                if (response != null) {
                    establishmentData.value = response
                }
                isLoading.value = false
            } catch (e: HttpException) {
                errorMessage.value = "Error: ${e.message}"
                isLoading.value = false
            } catch (e: Throwable) {
                errorMessage.value = e.toString()
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
        establishmentData.value?.let { establishment ->
            Column() {
                EventCard(title = establishment.name, place ="" , imageUrl = establishment.logoUrl, id = 1 )

                Text(text = "Name: ${establishment.name}" )
                Text(text = "Type: ${establishment.type}")
                Text(text = "Location: ${establishment.location ?: "N/A"}", )
                Text(text = "Date: ${establishment.date ?: "N/A"}")
                Spacer(modifier = Modifier.height(8.dp))
                Spacer(modifier = Modifier.height(16.dp))
                GlobalButton(onClick = { /*TODO*/ }, buttonText ="Reserve a Table" , isLoading =false ,modifier = Modifier.padding(8.dp,4.dp))

            }
        }
    }
}
