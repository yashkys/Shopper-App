package com.example.myapplication.di

import com.example.myapplication.ui.feature.cart.CartViewModel
import com.example.myapplication.ui.feature.home.HomeViewModel
import com.example.myapplication.ui.feature.product_details.ProductDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(get(), get())
    }
    viewModel {
        ProductDetailsViewModel(get())
    }
    viewModel {
        CartViewModel(get())
    }
}