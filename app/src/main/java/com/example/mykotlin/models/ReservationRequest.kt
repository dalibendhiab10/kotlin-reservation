package com.example.mykotlin.models

data class ReservationRequest(
    val user: User,
    val establishment: Establishment,
    val reservationDateTime: String, // ISO 8601 format
    val numberOfGuests: Int,
    val status: String
)

data class User(
    val id: Int
)

data class Establishment(
    val id: Int
)
