package com.example.starwars.categories.domain

import com.example.starwars.categories.data.model.CategoriesRepository
import com.example.starwars.categories.domain.data.Category

interface CategoriesInteractor {
    suspend fun getCategories(): Array<Category>
}

class CategoriesInteractorImpl(
    private val repository: CategoriesRepository,
    private val mapper: CategoriesMapper
) : CategoriesInteractor {
    override suspend fun getCategories(): Array<Category> {
        return mapper.toCategories(repository.getCategories())
    }
}