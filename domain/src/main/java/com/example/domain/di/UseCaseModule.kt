package com.example.domain.di

import com.example.domain.usecase.AddProductToCartUseCase
import com.example.domain.usecase.GetCategoryUseCase
import com.example.domain.usecase.GetProductUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetProductUseCase(get()) }
    factory { GetCategoryUseCase(get()) }
    factory { AddProductToCartUseCase(get()) }
    factory { GetCategoryUseCase(get()) }
}