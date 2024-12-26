//package com.example.mykotlin.ui.screens.consumer
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.runtime.Composable
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material3.*
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import coil.compose.rememberAsyncImagePainter
//import com.example.mykotlin.R
//import com.example.mykotlin.models.ReservationData
//
//
//@Composable
//fun ReservationScreen(
//    onBackPressed: () -> Unit,
//    onFinalizeReservation: (ReservationData) -> Unit
//) {
//    var selectedDate by remember { mutableStateOf("VENDREDI 20") }
//    var selectedTime by remember { mutableStateOf<String?>(null) }
//    var selectedPax by remember { mutableStateOf<Int?>(null) }
//    var selectedLocation by remember { mutableStateOf<String?>(null) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .systemBarsPadding()
//    ) {
//        EventDetails()
//        DateSection(selectedDate) { selectedDate = it }
//        TimeSection(selectedTime) { selectedTime = it }
//        PaxSection(selectedPax) { selectedPax = it }
//        LocationSection(selectedLocation) { selectedLocation = it }
//        Spacer(modifier = Modifier.weight(1f))
//        FinalizeButton(onClick = { /* Handle reservation */ })
//    }
//}
//
//
//@Composable
//private fun EventDetails() {
//    val imageUrl = "https://your-image-link.com/event_image.jpg" // Replace with your image URL
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(240.dp)
//    ) {
//        Image(
//            painter = rememberAsyncImagePainter(model = imageUrl),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
//
//
//    }
//
//    Text(
//        text = "Bee Hive Club",
//        color = Color(0xFF1A1A1A),
//        fontSize = 24.sp,
//        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
//    )
//
//    Text(
//        text = "Mortadha Ftiti",
//        color = Color.White,
//        fontSize = 20.sp,
//        modifier = Modifier.padding(horizontal = 16.dp)
//    )
//}
//
//
//@Composable
//private fun DateSection(
//    selectedDate: String,
//    onDateSelected: (String) -> Unit
//) {
//    Column {
//        Row(
//            modifier = Modifier.padding(horizontal = 16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(
//                imageVector = Icons.Default.DateRange,
//                contentDescription = null,
//                tint = Color.White
//            )
//            Text(
//                text = "Quand : ",
//                color = Color.White,
//                modifier = Modifier.padding(start = 8.dp)
//            )
//            Text(
//                text = "VENDREDI 20 DÉCEMBRE",
//                color = Color(0xFFFF9800),
//                modifier = Modifier.padding(start = 8.dp)
//            )
//        }
//
//        Row(
//            modifier = Modifier
//                .padding(horizontal = 16.dp, vertical = 8.dp)
//                .fillMaxWidth()
//        ) {
//            SelectableChip(
//                text = "VENDREDI\n20",
//                isSelected = selectedDate == "VENDREDI 20",
//                onClick = { onDateSelected("VENDREDI 20") }
//            )
//            SelectableChip(
//                text = "SAMEDI\n21",
//                isSelected = selectedDate == "SAMEDI 21",
//                onClick = { onDateSelected("SAMEDI 21") }
//            )
//        }
//    }
//}
//
//@Composable
//private fun TimeSection(
//    selectedTime: String?,
//    onTimeSelected: (String) -> Unit
//) {
//    Column {
//        Row(
//            modifier = Modifier.padding(horizontal = 16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(
//                imageVector = Icons.Default.Schedule,
//                contentDescription = null,
//                tint = Color.White
//            )
//            Text(
//                text = "Heure : ",
//                color = Color.White,
//                modifier = Modifier.padding(start = 8.dp)
//            )
//            Text(
//                text = selectedTime ?: "Selectionner",
//                color = Color(0xFFFF9800),
//                modifier = Modifier.padding(start = 8.dp)
//            )
//        }
//
//        LazyRow(
//            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
//        ) {
//            items(listOf("20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00")) { time ->
//                SelectableChip(
//                    text = time,
//                    isSelected = selectedTime == time,
//                    onClick = { onTimeSelected(time) }
//                )
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//private fun PaxSection(
//    selectedPax: Int?,
//    onPaxSelected: (Int) -> Unit
//) {
//    Column {
//        Row(
//            modifier = Modifier.padding(horizontal = 16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(
//                imageVector = Icons.Default.Person,
//                contentDescription = null,
//                tint = Color.White
//            )
//            Text(
//                text = "Combien : ",
//                color = Color.White,
//                modifier = Modifier.padding(start = 8.dp)
//            )
//            Text(
//                text = "${selectedPax ?: 0} Pax",
//                color = Color(0xFFFF9800),
//                modifier = Modifier.padding(start = 8.dp)
//            )
//        }
//
//        Row(
//            modifier = Modifier
//                .padding(horizontal = 16.dp, vertical = 8.dp)
//                .fillMaxWidth()
//        ) {
//            (1..5).forEach { pax ->
//                SelectableChip(
//                    text = "$pax Pax",
//                    isSelected = selectedPax == pax,
//                    onClick = { onPaxSelected(pax) }
//                )
//            }
//        }
//    }
//}
//
//@Composable
//private fun LocationSection(
//    selectedLocation: String?,
//    onLocationSelected: (String) -> Unit
//) {
//    Column {
//        Row(
//            modifier = Modifier.padding(horizontal = 16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(
//                imageVector = Icons.Default.Place,
//                contentDescription = null,
//                tint = Color.White
//            )
//            Text(
//                text = "Où : ",
//                color = Color.White,
//                modifier = Modifier.padding(start = 8.dp)
//            )
//        }
//
//        Row(
//            modifier = Modifier
//                .padding(horizontal = 16.dp, vertical = 8.dp)
//                .fillMaxWidth()
//        ) {
//            SelectableChip(
//                text = "Bistrot",
//                isSelected = selectedLocation == "Bistrot",
//                onClick = { onLocationSelected("Bistrot") }
//            )
//            SelectableChip(
//                text = "Salon",
//                isSelected = selectedLocation == "Salon",
//                onClick = { onLocationSelected("Salon") }
//            )
//        }
//    }
//}
//
//@Composable
//private fun FinalizeButton(onClick: () -> Unit) {
//    Button(
//        onClick = onClick,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//
//        border = BorderStroke(1.dp, Color.White)
//    ) {
//        Text(
//            text = "Finaliser la réservation",
//            color = Color.White,
//            modifier = Modifier.padding(vertical = 8.dp)
//        )
//    }
//}
