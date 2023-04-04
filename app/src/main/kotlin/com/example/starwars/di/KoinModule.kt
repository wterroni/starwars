package com.example.starwars.di

import android.content.Context
import org.koin.android.ext.koin.androidContext

fun startKoin(context: Context) {
    org.koin.core.context.startKoin {
        androidContext(context)
        modules(appModules + viewModelsModule)
    }
}

val appModules = listOf(
    dataModule
)