package com.example.data.repository

import android.util.Log
import com.example.domain.model.request.AddToCartRequest
import com.example.domain.model.response.CartResponse
import com.example.domain.network.NetworkService
import com.example.domain.network.ResultWrapper
import com.example.domain.repository.CartRepository

class CartRepositoryImpl(
    private val networkService: NetworkService
) : CartRepository {
    override suspend fun addProductToCart(
        request: AddToCartRequest
    ): ResultWrapper<CartResponse> {
        return networkService.addProductToCart(request)
    }

    override suspend fun getCart(): ResultWrapper<CartResponse> {
        return networkService.getCart()
    }

}
