package com.example.starwars.categories.domain

import com.example.starwars.categories.data.CategoriesRepository
import com.example.starwars.categories.domain.model.Category

interface CategoriesInteractor {
    suspend fun getCategories(): List<Category>
    fun getFavorites(): ArrayList<Category>
    fun saveFavorite(Category: Category)
    fun removeFavorite(Category: Category)
    fun updateLocalFavorites(category: List<Category>): List<Category>
}

class CategoriesInteractorImpl(
    private val repository: CategoriesRepository,
    private val mapper: CategoriesMapper
) : CategoriesInteractor {
    override suspend fun getCategories(): List<Category> {
        return addFavorites(mapper.toCategories(repository.getCategories()))
    }

    override fun getFavorites(): ArrayList<Category> =
        mapper.favoriteToCategory(repository.getFavorite())

    override fun saveFavorite(Category: Category) =
        repository.saveFavorite(mapper.toFavorite(Category))

    override fun removeFavorite(Category: Category) =
        repository.removeFavorite(mapper.toFavorite(Category))

    override fun updateLocalFavorites(category: List<Category>): List<Category> =
        addFavorites(category)


    private fun addFavorites(category: List<Category>): List<Category> {
        val favorites = repository.getFavorite()

        return category.mapIndexed { _, character ->
            character.copy(isFavority = (favorites.any { it.id == character.id }))
        }
    }
}