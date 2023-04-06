package com.example.starwars.categories.presentation

import com.example.starwars.categories.domain.model.Category

interface IFavoriteHandle {
    fun saveFavorite(categoryModel: Category)
    fun deleteFavorite(categoryModel: Category)
}