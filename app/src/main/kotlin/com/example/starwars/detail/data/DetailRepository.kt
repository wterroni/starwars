package com.example.starwars.detail.data

import com.example.starwars.data.StarWarsService
import com.example.starwars.data.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface DetailRepository {
    suspend fun getFilmsDetail(): FilmsModel
    suspend fun getPeopleDetail(): PeopleModel
    suspend fun getPlanetsDetail(): PlanetsModel
    suspend fun getSpeciesDetail(): SpeciesModel
    suspend fun getVehiclesDetail(): VehiclesModel
    suspend fun getStarshipsDetail(): StarShipsModel
}

class DetailRepositoryImpl(
    private val service: StarWarsService
) : DetailRepository {
    override suspend fun getFilmsDetail(): FilmsModel =
        withContext(Dispatchers.IO) { service.getFilmsDetail() }

    override suspend fun getPeopleDetail(): PeopleModel =
        withContext(Dispatchers.IO) { service.getPeopleDetail() }

    override suspend fun getPlanetsDetail(): PlanetsModel =
        withContext(Dispatchers.IO) { service.getPlanetsDetail() }

    override suspend fun getSpeciesDetail(): SpeciesModel =
        withContext(Dispatchers.IO) { service.getSpeciesDetail() }

    override suspend fun getVehiclesDetail(): VehiclesModel =
        withContext(Dispatchers.IO) { service.getVehiclesDetail() }

    override suspend fun getStarshipsDetail(): StarShipsModel =
        withContext(Dispatchers.IO) { service.getStarshipsDetail() }

}