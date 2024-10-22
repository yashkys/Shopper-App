package com.example.data.repository

import com.example.domain.model.response.CategoryResponse
import com.example.domain.network.NetworkService
import com.example.domain.network.ResultWrapper
import com.example.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val networkService: NetworkService
) : CategoryRepository {
    override suspend fun getCategories(): ResultWrapper<CategoryResponse> {
        return networkService.getCategories()
    }

}
