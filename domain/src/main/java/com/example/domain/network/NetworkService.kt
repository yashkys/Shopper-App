package com.example.domain.network

import com.example.domain.model.Product
import com.example.domain.model.response.CategoryResponse
import com.example.domain.model.response.ProductResponse
import java.lang.Exception

interface NetworkService {
    suspend fun getProducts(category: Int?): ResultWrapper<ProductResponse>
    suspend fun getCategories(): ResultWrapper<CategoryResponse>

}

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class Failure(val exception: Exception): ResultWrapper<Nothing>()
}