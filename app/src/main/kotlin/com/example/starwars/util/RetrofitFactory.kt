package com.example.starwars.util

import com.example.starwars.data.StarWarsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private const val BASE_URL = "https://swapi.dev/api/"
    fun makeRetrofitService(): StarWarsService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(StarWarsService::class.java)
    }
}