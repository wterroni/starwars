package com.example.starwars.categories.data

import com.example.starwars.categories.data.model.CategoriesModel
import com.example.starwars.categories.domain.model.FavoriteCategoryModel
import com.example.starwars.categories.presentation.CategoryUiModel
import com.example.starwars.data.StarWarsService
import io.mockk.every
import io.mockk.justRun
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import io.mockk.mockk
import io.mockk.verify

class CategoriesRepositoryTest {
    private val service: StarWarsService = mockk()
    private val uiModel: CategoryUiModel = mockk()

    private val repository = CategoriesRepositoryImpl(service, uiModel)

    @Test
    fun `get character return success`() {
        val response = mockk<CategoriesModel>()
        every {
            runBlocking {
                service.getCategories()
            }
        } returns response

        runBlocking {
            val result = repository.getCategories()
            Assert.assertEquals(result, response)
        }

    }

    @Test
    fun `get favorite character return success`() {
        val response = mockk<ArrayList<FavoriteCategoryModel>>()
        every {
            runBlocking {
                uiModel.getFavorite()
            }
        } returns response

        runBlocking {
            val result = repository.getFavorite()
            Assert.assertEquals(result, response)
        }
    }

    @Test
    fun `save favorite character success`() {
        val saveData = mockk<FavoriteCategoryModel>()
        justRun  {
            runBlocking {
                uiModel.saveFavorite(any())
            }
        }

        runBlocking {
            repository.saveFavorite(saveData)
            verify { uiModel.saveFavorite(saveData) }
        }
    }

    @Test
    fun `remove favorite character success`() {
        val removeData = mockk<FavoriteCategoryModel>()
        justRun  {
            runBlocking {
                uiModel.removeFavorite(any())
            }
        }

        runBlocking {
            repository.removeFavorite(removeData)
            verify { uiModel.removeFavorite(removeData) }
        }
    }
}