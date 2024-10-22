package com.example.data.repository

import com.example.domain.model.Product
import com.example.domain.model.response.ProductResponse
import com.example.domain.network.NetworkService
import com.example.domain.network.ResultWrapper
import com.example.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val networkService: NetworkService
): ProductRepository {
    override suspend fun getProducts(category: Int?): ResultWrapper<ProductResponse> {
        return networkService.getProducts(category)
    }
}