package com.example.starwars.detail.di

import com.example.starwars.detail.data.DetailRepository
import com.example.starwars.detail.data.DetailRepositoryImpl
import com.example.starwars.detail.domain.DetailInteractor
import com.example.starwars.detail.domain.DetailInteractorImpl
import com.example.starwars.detail.domain.model.DetailMapper
import com.example.starwars.detail.domain.model.DetailMapperImpl
import com.example.starwars.detail.presentation.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val detailViewModelsModule = module {
    viewModel { DetailViewModel(get()) }

    single { DetailInteractorImpl(get(), get()) } bind DetailInteractor::class
    single { DetailRepositoryImpl(get()) } bind DetailRepository::class
    single { DetailMapperImpl() } bind DetailMapper::class
}