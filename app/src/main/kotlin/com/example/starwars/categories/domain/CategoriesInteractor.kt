package com.example.starwars.categories.domain

import com.example.starwars.categories.data.model.CategoriesRepository
import com.example.starwars.categories.domain.data.CategoriesVO

interface CategoriesInteractor {
    suspend fun getCategories(): CategoriesVO
}

class CategoriesInteractorImpl(
    private val repository: CategoriesRepository,
    private val mapper: CategoriesMapper
) : CategoriesInteractor {
    override suspend fun getCategories(): CategoriesVO {
        return mapper.toCategories(repository.getCategories())
    }
}