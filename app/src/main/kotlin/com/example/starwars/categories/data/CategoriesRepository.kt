package com.example.starwars.categories.data.model

import com.example.starwars.data.StarWarsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CategoriesRepository {
    suspend fun getCategories(): CategoriesModel
}

class CategoriesRepositoryImpl(
    private val service: StarWarsService
): CategoriesRepository {
    override suspend fun getCategories(): CategoriesModel =
        withContext(Dispatchers.IO) {
            service.getCategories()
        }
}