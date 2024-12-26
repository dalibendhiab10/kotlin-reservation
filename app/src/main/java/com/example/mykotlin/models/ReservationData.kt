package com.example.mykotlin.models

data class ReservationData(
    val date: String,
    val time: String,
    val pax: Int,
    val location: String
)