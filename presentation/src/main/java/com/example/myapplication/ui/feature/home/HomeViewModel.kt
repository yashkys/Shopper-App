package com.example.myapplication.ui.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Product
import com.example.domain.network.ResultWrapper
import com.example.domain.usecase.GetProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.logger.MESSAGE

class HomeViewModel(
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    public val uiState = _uiState.asStateFlow()

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            _uiState.value = HomeScreenUIEvents.Loading
            val featuredProducts = getProducts("electronics")
            val popularProducts = getProducts("jewelery")
            if (featuredProducts.isEmpty() || popularProducts.isEmpty()) {
                _uiState.value = HomeScreenUIEvents.Error("Failed to load products")
                return@launch
            }
            _uiState.value = HomeScreenUIEvents.Success(
                featuredProducts = featuredProducts,
                popularProducts = popularProducts,
            )
        }
    }

    private suspend fun getProducts(
        category: String? = null
    ): List<Product> {
        getProductUseCase.execute(category).let { result ->
            when (result) {
                is ResultWrapper.Success -> {
                    Log.i("APP data", result.value.toString())
//                    val data = (result as ResultWrapper.Success).value
//                    _uiState.value = HomeScreenUIEvents.Success(data)
                    return (result).value
                }

                is ResultWrapper.Failure -> {
                    Log.i("APP data", result.exception.message?: "Something went wrong.")
//                    val error =
//                        (result as ResultWrapper.Failure).exception.message ?: "Something went wrong."
//                    _uiState.value = HomeScreenUIEvents.Error(error)
                    return emptyList()
                }
            }
        }
    }

}

sealed class HomeScreenUIEvents {
    data object Loading : HomeScreenUIEvents()
    data class Success(
        val featuredProducts: List<Product>,
        val popularProducts: List<Product>,
    ) : HomeScreenUIEvents()
    data class Error(val message: String) : HomeScreenUIEvents()


}