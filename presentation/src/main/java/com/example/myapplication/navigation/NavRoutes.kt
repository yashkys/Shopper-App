package com.example.myapplication.navigation

import com.example.domain.model.Product
import com.example.myapplication.model.UiProductModel
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
object CartScreen

@Serializable
object ProfileScreen

@Serializable
data class ProductDetails(val product: UiProductModel)