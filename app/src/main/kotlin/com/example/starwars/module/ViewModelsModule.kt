package com.example.starwars.module

import com.example.starwars.presentation.StarWarsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { StarWarsViewModel() }
}