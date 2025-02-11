package com.example.rabotablyat.models

data class Order(
    val id: String = "",
    val userId: String = "",
    val flowerId: String = "",
    val quantity: Int = 0,
    val totalPrice: Double = 0.0,
    val status: String = "pending"
)