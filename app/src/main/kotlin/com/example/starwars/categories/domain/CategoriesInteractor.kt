package com.example.starwars.categories.domain

import com.example.starwars.categories.data.CategoriesRepository
import com.example.starwars.categories.domain.model.Category

interface CategoriesInteractor {
    suspend fun getCategories(): Array<Category>
    fun saveFavorite(Category: Category)
    fun removeFavorite(Category: Category)
    fun updateLocalFavorites(category: ArrayList<Category>): ArrayList<Category>
}

class CategoriesInteractorImpl(
    private val repository: CategoriesRepository,
    private val mapper: CategoriesMapper
) : CategoriesInteractor {
    override suspend fun getCategories(): Array<Category> {
        return mapper.toCategories(repository.getCategories())
    }

    override fun saveFavorite(Category: Category) =
        repository.saveFavorite(mapper.toFavorite(Category))

    override fun removeFavorite(Category: Category) =
        repository.removeFavorite(mapper.toFavorite(Category))

    override fun updateLocalFavorites(category: ArrayList<Category>): ArrayList<Category> =
        addFavorites(category)


    private fun addFavorites(category: ArrayList<Category>): ArrayList<Category> {
        val favorites = repository.getFavorite()

        return category.mapIndexed { _, character ->
            character.copy(isFavority = (favorites.any { it.id == character.id }))
        } as ArrayList
    }
}