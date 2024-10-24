package com.example.data.model.request

import com.example.domain.model.request.AddToCartRequest
import kotlinx.serialization.Serializable

@Serializable
data class AddToCartDataRequest(
    val productId: Int,
    val productName: String,
    val price: Double,
    val quantity: Int,
    val userId: Int, // Link cart item to the user
) {
    companion object {
        fun fromCartRequest(addCartRequestModel: AddToCartRequest) = AddToCartDataRequest(
            productId = addCartRequestModel.productId,
            productName = addCartRequestModel.productName,
            price = addCartRequestModel.price,
            quantity = addCartRequestModel.quantity,
            userId = addCartRequestModel.userId
        )
    }
}