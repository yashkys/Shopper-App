package com.example.domain.usecase

import com.example.domain.repository.CategoryRepository

class GetCategoryUseCase(
    private val repository: CategoryRepository
) {
    suspend fun execute() = repository.getCategories()
}
