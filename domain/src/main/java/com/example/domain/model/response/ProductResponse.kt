package com.example.domain.model.response

import com.example.domain.model.Product

data class ProductResponse(
    val products: List<Product>,
    val msg: String
)
