package com.example.starwars.categories.domain.model

data class FavoriteCategoryModel(
    var id: Int,
    val name: String,
    val url: String,
    val imageUrl: String,
    val categoryType: CategoryType,
    var isFavority: Boolean
)