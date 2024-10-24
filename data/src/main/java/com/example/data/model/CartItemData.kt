package com.example.data.model

import com.example.domain.model.CartItem
import kotlinx.serialization.Serializable

@Serializable
data class CartItemData(
    val id: Int,
    val productId: Int,
    val userId: Int,
    val name: String,
    val price: Double,
    val imageUrl: String?,
    val quantity: Int,
    val productName: String
){
    fun toCartItem(): CartItem {
        return CartItem(
            id = id,
            productId = productId,
            userId = userId,
            name = name,
            price = price,
            imageUrl = imageUrl,
            quantity = quantity,
            productName = productName
        )
    }
}
