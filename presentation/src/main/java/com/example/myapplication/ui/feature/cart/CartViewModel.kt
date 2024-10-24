package com.example.myapplication.ui.feature.cart

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetCartUseCase
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CartItem
import com.example.domain.network.ResultWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    val cartUseCase: GetCartUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<CartEvent>(CartEvent.Loading)
    val uiState = _uiState.asStateFlow()
    init {
        getCart()
    }
    fun getCart() {
        viewModelScope.launch {
            _uiState.value = CartEvent.Loading
            cartUseCase.execute().let { result ->
                when (result) {
                    is ResultWrapper.Success -> {
                        _uiState.value = CartEvent.Success(result.value.data)
                    }
                    is ResultWrapper.Failure -> {
                        _uiState.value = CartEvent.Error("Something went wrong! ${result.exception.message}")
                    }
                }
            }
        }
    }
}

sealed  class CartEvent {
    data object Loading : CartEvent()
    data class Success(val message: List<CartItem>) : CartEvent()
    data class Error(val message: String) : CartEvent()
}
