package com.example.starwars.di

import com.example.starwars.categories.di.categoriesViewModelsModule
import com.example.starwars.detail.di.detailViewModelsModule

val viewModelsModule = listOf(categoriesViewModelsModule, detailViewModelsModule)