package com.example.starwars.categories.data

import com.example.starwars.categories.data.model.CategoriesModel
import com.example.starwars.categories.domain.model.FavoriteCategoryModel
import com.example.starwars.categories.presentation.CategoryUiModel
import com.example.starwars.data.StarWarsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CategoriesRepository {
    suspend fun getCategories(): CategoriesModel
    fun getFavorite(): ArrayList<FavoriteCategoryModel>
    fun saveFavorite(category: FavoriteCategoryModel)
    fun removeFavorite(category: FavoriteCategoryModel)
}

class CategoriesRepositoryImpl(
    private val service: StarWarsService,
    private val uiModel: CategoryUiModel
): CategoriesRepository {
    override suspend fun getCategories(): CategoriesModel =
        withContext(Dispatchers.IO) {
            service.getCategories()
        }

    override fun getFavorite(): ArrayList<FavoriteCategoryModel> = uiModel.getFavorite()

    override fun saveFavorite(categoryModel: FavoriteCategoryModel) =
        uiModel.saveFavorite(categoryModel)

    override fun removeFavorite(categoryModel: FavoriteCategoryModel) =
        uiModel.removeFavorite(categoryModel)
}