package com.example.mobilka.model

data class BasketModel(
    val item: String,
    val price: Int,
    var count: Int = 0
)
