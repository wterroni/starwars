package com.example.starwars.categories.domain.model

data class Category(
    val id: Int,
    val name: String,
    val url: String,
    val imageUrl: String,
    val categoryType: CategoryType,
    var isFavority: Boolean
)

enum class CategoryType {
    FILMS, PEOPLE, PLANETS, SPECIES, STARSHIPS, VEHICLES
}