package com.example.starwars.categories.domain

import com.example.starwars.categories.data.model.CategoriesModel
import com.example.starwars.categories.domain.model.Category
import com.example.starwars.categories.domain.model.CategoryType

interface CategoriesMapper {
    fun toCategories(categoriesModel: CategoriesModel): Array<Category>
}

class CategoriesMapperImpl : CategoriesMapper {
    override fun toCategories(categoriesModel: CategoriesModel): Array<Category> {
        return arrayOf(
            Category(
                url = categoriesModel.films,
                name = "Films",
                imageUrl = "https://starwars-visualguide.com/assets/img/categories/films.jpg",
                categoryType = CategoryType.FILMS
            ),
            Category(
                url = categoriesModel.people,
                name = "People",
                imageUrl = "https://starwars-visualguide.com/assets/img/categories/character.jpg",
                categoryType = CategoryType.PEOPLE
            ),
            Category(
                url = categoriesModel.planets,
                name = "Planets",
                imageUrl = "https://starwars-visualguide.com/assets/img/categories/planets.jpg",
                categoryType = CategoryType.PLANETS
            ),
            Category(
                url = categoriesModel.species,
                name = "Species",
                imageUrl = "https://starwars-visualguide.com/assets/img/categories/species.jpg",
                categoryType = CategoryType.SPECIES
            ),
            Category(
                url = categoriesModel.starships,
                name = "Starships",
                imageUrl = "https://starwars-visualguide.com/assets/img/categories/starships.jpg",
                categoryType = CategoryType.STARSHIPS
            ),
            Category(
                url = categoriesModel.vehicles,
                name = "Vehicles",
                imageUrl = "https://starwars-visualguide.com/assets/img/categories/vehicles.jpg",
                categoryType = CategoryType.VEHICLES
            )
        )
    }
}