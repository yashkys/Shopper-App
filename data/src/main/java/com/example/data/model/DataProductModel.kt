package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
class DataProductModel(
    val id: Long,
    val title: String,
    val price: Double,
    val category: String,
    val description: String,
    val image: String,
) {
    fun toProduct() = com.example.domain.model.Product(
        id = id,
        title = title,
        price = price,
        category = category,
        description = description,
        image = image,
    )
}