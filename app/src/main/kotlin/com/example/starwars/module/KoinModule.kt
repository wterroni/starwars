package com.example.starwars.module

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