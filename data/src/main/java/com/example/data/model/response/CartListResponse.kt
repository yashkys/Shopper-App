package com.example.data.model.response

import com.example.data.model.CartItemData
import com.example.domain.model.response.CartResponse
import kotlinx.serialization.Serializable

@Serializable
data class CartListResponse(
    val data: List<CartItemData>,
    val msg: String
) {
    fun toCart(): CartResponse {
        return CartResponse(
            data = data.map { it.toCartItem() },
            msg = msg
        )
    }
}