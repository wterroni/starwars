package com.example.starwars.categories

import com.example.starwars.categories.domain.model.Category
import com.example.starwars.categories.domain.model.CategoryType
import com.example.starwars.categories.domain.model.FavoriteCategoryModel

val mockTestFavorite = FavoriteCategoryModel(
    id = 1,
    name = "Films",
    url = "",
    imageUrl = "",
    categoryType = CategoryType.FILMS,
    isFavority = false
)

val mockCategory = Category(
    id = 1,
    name = "Films",
    url = "",
    imageUrl = "",
    categoryType = CategoryType.FILMS,
    isFavority = false
)

val mockArrayCategory = arrayListOf(
    mockCategory
)