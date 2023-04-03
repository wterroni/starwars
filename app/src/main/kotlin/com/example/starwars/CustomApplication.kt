package com.example.starwars

import android.app.Application
import com.example.starwars.module.startKoin


class CustomApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin (this@CustomApplication)
    }
}