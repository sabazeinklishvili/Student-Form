package com.example.carorder


data class Car(
    val name: String,
    val priceUsd: Double,
    @androidx.annotation.DrawableRes val imageResId: Int,
    val description: String
)
