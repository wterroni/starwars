package com.example.starwars.detail.domain

import com.example.starwars.detail.data.DetailRepository
import com.example.starwars.detail.domain.model.Detail
import com.example.starwars.detail.domain.model.DetailMapper

interface DetailInteractor {
    suspend fun getFilmsDetail(): List<Detail>
    suspend fun getPeopleDetail(): List<Detail>
    suspend fun getPlanetsDetail(): List<Detail>
    suspend fun getSpeciesDetail(): List<Detail>
    suspend fun getVehiclesDetail(): List<Detail>
    suspend fun getStarshipsDetail(): List<Detail>
}

class DetailInteractorImpl(
    private val repository: DetailRepository,
    private val mapper: DetailMapper
) : DetailInteractor {
    override suspend fun getFilmsDetail() = mapper.toFilmDetail(repository.getFilmsDetail())
    override suspend fun getPeopleDetail() = mapper.toPeopleDetail(repository.getPeopleDetail())
    override suspend fun getPlanetsDetail() = mapper.toPlanetsDetail(repository.getPlanetsDetail())
    override suspend fun getSpeciesDetail() = mapper.toSpeciesDetail(repository.getSpeciesDetail())
    override suspend fun getVehiclesDetail() = mapper.toVehiclesDetail(repository.getVehiclesDetail())
    override suspend fun getStarshipsDetail() = mapper.toStarShipsDetail(repository.getStarshipsDetail())

}