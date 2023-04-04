package com.example.starwars.data

import com.example.starwars.categories.data.model.CategoriesModel
import retrofit2.http.GET

interface StarWarsService {
    @GET(".")
    suspend fun getCategories(
    ): CategoriesModel
}
