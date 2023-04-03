package com.example.starwars.categories.domain

import com.example.starwars.categories.data.model.CategoriesModel
import com.example.starwars.categories.domain.data.CategoriesVO

interface CategoriesMapper {
    fun toCategories(categoriesModel: CategoriesModel): CategoriesVO
}

class CategoriesMapperImpl: CategoriesMapper {
    override fun toCategories(categoriesModel: CategoriesModel): CategoriesVO {
        return CategoriesVO(
            films = categoriesModel.films,
            people = categoriesModel.people,
            planets = categoriesModel.planets,
            species = categoriesModel.species,
            starships = categoriesModel.starships,
            vehicles = categoriesModel.vehicles
        )
    }
}