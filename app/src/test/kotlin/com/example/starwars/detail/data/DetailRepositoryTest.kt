package com.example.starwars.detail.data

import com.example.starwars.data.StarWarsService
import com.example.starwars.data.model.*
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class DetailRepositoryTest {
    private val service: StarWarsService = mockk()

    private val repository = DetailRepositoryImpl(service)

    @Test
    fun `get getFilmsDetail return success`() {
        val response = mockk<FilmsModel>()
        every {
            runBlocking {
                service.getFilmsDetail()
            }
        } returns response

        runBlocking {
            val result = repository.getFilmsDetail()
            Assert.assertEquals(result, response)
        }
    }

    @Test
    fun `get getPeopleDetail return success`() {
        val response = mockk<PeopleModel>()
        every {
            runBlocking {
                service.getPeopleDetail()
            }
        } returns response

        runBlocking {
            val result = repository.getPeopleDetail()
            Assert.assertEquals(result, response)
        }
    }

    @Test
    fun `get getSpeciesDetail return success`() {
        val response = mockk<SpeciesModel>()
        every {
            runBlocking {
                service.getSpeciesDetail()
            }
        } returns response

        runBlocking {
            val result = repository.getSpeciesDetail()
            Assert.assertEquals(result, response)
        }
    }

    @Test
    fun `get getPlanetsDetail return success`() {
        val response = mockk<PlanetsModel>()
        every {
            runBlocking {
                service.getPlanetsDetail()
            }
        } returns response

        runBlocking {
            val result = repository.getPlanetsDetail()
            Assert.assertEquals(result, response)
        }
    }

    @Test
    fun `get getStarshipsDetail return success`() {
        val response = mockk<StarShipsModel>()
        every {
            runBlocking {
                service.getStarshipsDetail()
            }
        } returns response

        runBlocking {
            val result = repository.getStarshipsDetail()
            Assert.assertEquals(result, response)
        }
    }

    @Test
    fun `get getVehiclesDetail return success`() {
        val response = mockk<VehiclesModel>()
        every {
            runBlocking {
                service.getVehiclesDetail()
            }
        } returns response

        runBlocking {
            val result = repository.getVehiclesDetail()
            Assert.assertEquals(result, response)
        }
    }
}