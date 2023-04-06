package com.example.starwars.di

import com.example.starwars.data.StarWarsService
import com.example.starwars.util.RetrofitFactory
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    single { RetrofitFactory.makeRetrofitService() } bind StarWarsService::class
}