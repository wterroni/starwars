package com.example.starwars.categories.domain

import com.example.starwars.categories.data.model.CategoriesModel
import com.example.starwars.categories.domain.model.Category
import com.example.starwars.categories.domain.model.CategoryType
import com.example.starwars.categories.domain.model.FavoriteCategoryModel

interface CategoriesMapper {
    fun toCategories(categoriesModel: CategoriesModel): Array<Category>
    fun favoriteToCharacter(favoriteCategory: ArrayList<FavoriteCategoryModel>): ArrayList<Category>
    fun toFavorite(characterModel: Category): FavoriteCategoryModel
}

class CategoriesMapperImpl : CategoriesMapper {
    override fun toCategories(categoriesModel: CategoriesModel): Array<Category> {
        return arrayOf(
            Category(
                id = 1,
                url = categoriesModel.films,
                name = "Films",
                imageUrl = "https://starwars-visualguide.com/assets/img/categories/films.jpg",
                categoryType = CategoryType.FILMS,
                isFavority = false
            ),
            Category(
                id = 2,
                url = categoriesModel.people,
                name = "People",
                imageUrl = "https://starwars-visualguide.com/assets/img/categories/character.jpg",
                categoryType = CategoryType.PEOPLE,
                isFavority = false
            ),
            Category(
                id = 3,
                url = categoriesModel.planets,
                name = "Planets",
                imageUrl = "https://starwars-visualguide.com/assets/img/categories/planets.jpg",
                categoryType = CategoryType.PLANETS,
                isFavority = false
            ),
            Category(
                id = 4,
                url = categoriesModel.species,
                name = "Species",
                imageUrl = "https://starwars-visualguide.com/assets/img/categories/species.jpg",
                categoryType = CategoryType.SPECIES,
                isFavority = false
            ),
            Category(
                id = 5,
                url = categoriesModel.starships,
                name = "Starships",
                imageUrl = "https://starwars-visualguide.com/assets/img/categories/starships.jpg",
                categoryType = CategoryType.STARSHIPS,
                isFavority = false
            ),
            Category(
                id = 6,
                url = categoriesModel.vehicles,
                name = "Vehicles",
                imageUrl = "https://starwars-visualguide.com/assets/img/categories/vehicles.jpg",
                categoryType = CategoryType.VEHICLES,
                isFavority = false
            )
        )
    }

    override fun favoriteToCharacter(favoriteCategory: ArrayList<FavoriteCategoryModel>): ArrayList<Category> {
        val categories = ArrayList<Category>()

        for (category in favoriteCategory) {
            categories.add(
                Category(
                    id = category.id,
                    url = category.url,
                    name = category.name,
                    imageUrl = category.imageUrl,
                    categoryType = category.categoryType,
                    isFavority = true
                )
            )
        }

        return categories
    }

    override fun toFavorite(characterModel: Category): FavoriteCategoryModel {
        return FavoriteCategoryModel(
            characterModel.id,
            characterModel.url,
            characterModel.name,
            characterModel.imageUrl,
            characterModel.categoryType,
            characterModel.isFavority,
        )
    }
}