package com.example.mykotlin.models

data class EstablishmentData(
    val id:Number,
    val name: String,
    val type: String,
    val date: String,
    val logoUrl: String,
    val location: String?
)
