package com.example.domain.model.response

import com.example.domain.model.Category

data class CategoryResponse(
    val categories: List<Category>,
    val msg: String

)
