package com.example.domain.usecase

import com.example.domain.model.request.AddToCartRequest
import com.example.domain.repository.CartRepository

class AddProductToCartUseCase(
    private val cartRepository: CartRepository
) {
    suspend fun execute(
        request: AddToCartRequest
    ) = cartRepository.addProductToCart(request)
}