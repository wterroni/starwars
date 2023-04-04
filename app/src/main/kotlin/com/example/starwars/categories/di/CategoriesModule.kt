package com.example.starwars.categories.di

import com.example.starwars.categories.data.CategoriesRepository
import com.example.starwars.categories.data.CategoriesRepositoryImpl
import com.example.starwars.categories.domain.CategoriesInteractor
import com.example.starwars.categories.domain.CategoriesInteractorImpl
import com.example.starwars.categories.domain.CategoriesMapper
import com.example.starwars.categories.domain.CategoriesMapperImpl
import com.example.starwars.categories.presentation.CategoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val categoriesViewModelsModule = module {
    viewModel { CategoriesViewModel(get()) }

    single { CategoriesInteractorImpl(get(), get()) } bind CategoriesInteractor::class
    single { CategoriesRepositoryImpl(get()) } bind CategoriesRepository::class
    single { CategoriesMapperImpl() } bind CategoriesMapper::class
}