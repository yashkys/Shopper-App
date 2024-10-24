package com.example.myapplication.ui.feature.product_details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.request.AddToCartRequest
import com.example.domain.network.ResultWrapper
import com.example.domain.usecase.AddProductToCartUseCase
import com.example.myapplication.model.UiProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    val addProductToCartUseCase: AddProductToCartUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<ProductDetailsEvent>(ProductDetailsEvent.Nothing)
    val state = _state.asStateFlow()

    fun addProductToCart(product: UiProductModel) {
        viewModelScope.launch {
            _state.value = ProductDetailsEvent.Loading
            val result = addProductToCartUseCase.execute(
                AddToCartRequest(
                    product.id,
                    product.title,
                    product.price,
                    1,
                    1
                )
            )
            when (result) {
                is ResultWrapper.Success -> {
                    _state.value = ProductDetailsEvent.Success("Product added to cart")
                }

                is ResultWrapper.Failure -> {
                    Log.e("APP data Error",
                        result.exception.message
                            ?: "Something went wrong while adding product to cart.\n ${result.exception.message}"
                    )

                    _state.value = ProductDetailsEvent.Error("Something went wrong!")
                }
            }
        }
    }

}

sealed class ProductDetailsEvent {
    data object Loading : ProductDetailsEvent()
    data object Nothing : ProductDetailsEvent()
    data class Success(val message: String) : ProductDetailsEvent()
    data class Error(val message: String) : ProductDetailsEvent()
}