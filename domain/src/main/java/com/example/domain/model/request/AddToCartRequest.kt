package com.example.domain.model.request

data class AddToCartRequest(
    val productId: Int,
    val productName: String,
    val price: Double,
    val quantity: Int,
    val userId: Int,// Link cart item to the user
) {
}