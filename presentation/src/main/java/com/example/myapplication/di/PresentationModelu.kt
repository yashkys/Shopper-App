package com.example.myapplication.di

import org.koin.dsl.module

val presentationModule = module {
    includes(viewModelModule)
}