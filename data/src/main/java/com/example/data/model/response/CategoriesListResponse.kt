package com.example.data.model.response

import com.example.data.model.CategoryDataModel
import com.example.domain.model.response.CategoryResponse
import kotlinx.serialization.Serializable

@Serializable
data class CategoriesListResponse(
    val `data`: List<CategoryDataModel>,
    val msg: String
) {
    fun toCategoryList() : CategoryResponse {
        return CategoryResponse(
            categories = data.map { it.toCategory() },
            msg = msg
        )
    }
}
