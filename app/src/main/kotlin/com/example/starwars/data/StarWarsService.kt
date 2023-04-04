package com.example.starwars.data

import com.example.starwars.categories.data.model.CategoriesModel
import com.example.starwars.data.model.*
import retrofit2.http.GET

interface StarWarsService {
    @GET(".")
    suspend fun getCategories(
    ): CategoriesModel

    @GET("people/")
    suspend fun getPeopleDetail(
    ): PeopleModel

    @GET("planets/")
    suspend fun getPlanetsDetail(
    ): PlanetsModel

    @GET("films/")
    suspend fun getFilmsDetail(
    ): FilmsModel

    @GET("species/")
    suspend fun getSpeciesDetail(
    ): SpeciesModel

    @GET("vehicles/")
    suspend fun getVehiclesDetail(
    ): VehiclesModel

    @GET("starships/")
    suspend fun getStarshipsDetail(
    ): StarShipsModel
}
