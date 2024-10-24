package com.example.domain.usecase

import com.example.domain.repository.CartRepository

class GetCartUseCase(
    private val cartRepository: CartRepository
) {
    suspend fun execute() = cartRepository.getCart()
}