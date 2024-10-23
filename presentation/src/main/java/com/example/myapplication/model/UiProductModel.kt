package com.example.myapplication.model

import android.os.Parcelable
import com.example.domain.model.Product
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class UiProductModel(
    val categoryId: Int,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val title :String
): Parcelable {
    companion object{
        fun fromProduct(product: Product): UiProductModel {
            return UiProductModel(
                categoryId = product.categoryId,
                description = product.description,
                id = product.id,
                image = product.image,
                title = product.title,
                price = product.price
            )
        }
    }
}

