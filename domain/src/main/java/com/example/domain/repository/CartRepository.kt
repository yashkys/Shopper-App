package com.example.domain.repository

import com.example.domain.model.request.AddToCartRequest
import com.example.domain.model.response.CartResponse
import com.example.domain.network.ResultWrapper

interface CartRepository {
    suspend fun addProductToCart(
        request: AddToCartRequest
    ): ResultWrapper<CartResponse>

    suspend fun getCart(): ResultWrapper<CartResponse>
}