package com.example.starwars.categories.domain.model

data class Category(
    val name: String,
    val url: String,
    val imageUrl: String,
    val categoryType: CategoryType
)

enum class CategoryType {
    FILMS, PEOPLE, PLANETS, SPECIES, STARSHIPS, VEHICLES
}