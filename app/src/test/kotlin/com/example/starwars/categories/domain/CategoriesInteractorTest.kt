package com.example.starwars.categories.domain

import com.example.starwars.categories.data.CategoriesRepository
import com.example.starwars.categories.data.model.CategoriesModel
import com.example.starwars.categories.domain.model.Category
import com.example.starwars.categories.domain.model.FavoriteCategoryModel
import com.example.starwars.categories.mockArrayCategory
import com.example.starwars.categories.mockCategory
import com.example.starwars.categories.mockTestFavorite
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CategoriesInteractorTest {
    private val repository: CategoriesRepository = mockk()
    private val mapper: CategoriesMapper = mockk()

    private val characterInteractor = CategoriesInteractorImpl(repository, mapper)

    @Test
    fun `get categories return success`() {
        val response = mockk<CategoriesModel>()
        val favoriteResponse = ArrayList<FavoriteCategoryModel>()
        val mappedResponse = ArrayList<Category>()

        favoriteResponse.add(mockTestFavorite)

        every {
            runBlocking {
                repository.getCategories(
                )
            }
        } returns response

        every {
            runBlocking {
                repository.getFavorite()
            }
        } returns favoriteResponse

        every {
            runBlocking {
                mapper.toCategories(
                    any()
                )
            }
        } returns mappedResponse

        runBlocking {
            val result = characterInteractor.getCategories()
            Assert.assertTrue(result.isEmpty())
        }
    }

    @Test
    fun `get Favorite character return success`() {
        val response = mockk<ArrayList<Category>>()
        val favoriteResponse = mockk<ArrayList<FavoriteCategoryModel>>()

        every {
            runBlocking {
                repository.getFavorite()
            }
        } returns favoriteResponse

        every {
            runBlocking {
                mapper.toFavorite(
                    any()
                )
            }
        } returns mockTestFavorite

        every {
            runBlocking {
                mapper.favoriteToCategory(
                    any()
                )
            }
        } returns mockArrayCategory

        runBlocking {
            val result = characterInteractor.getFavorites()
            Assert.assertNotNull(result)
        }
    }

    @Test
    fun `save favorite character success`() {
        val saveData = mockk<Category>()
        val saveFavoriteData = mockk<FavoriteCategoryModel>()
        justRun {
            runBlocking {
                repository.saveFavorite(any())
            }
        }

        every {
            runBlocking {
                mapper.toFavorite(
                    any()
                )
            }
        } returns saveFavoriteData

        runBlocking {
            characterInteractor.saveFavorite(saveData)
            verify { repository.saveFavorite(any()) }
        }
    }

    @Test
    fun `remove favorite character success`() {
        val removeData = mockk<Category>()
        val removeFavoriteData = mockk<FavoriteCategoryModel>()
        justRun {
            runBlocking {
                repository.removeFavorite(any())
            }
        }
        every {
            runBlocking {
                mapper.toFavorite(
                    any()
                )
            }
        } returns removeFavoriteData

        runBlocking {
            characterInteractor.removeFavorite(removeData)
            verify { repository.removeFavorite(any()) }
        }
    }

    @Test
    fun `update local favorite character success`() {
        val favoriteResponse = ArrayList<FavoriteCategoryModel>()
        favoriteResponse.add((mockTestFavorite))
        val response = ArrayList<Category>()
        response.add(
            mockCategory
        )
        every {
            runBlocking {
                repository.getFavorite()
            }
        } returns favoriteResponse

        runBlocking {
            val result = characterInteractor.updateLocalFavorites(response)
            Assert.assertTrue(result.size == 1)
            Assert.assertTrue(result.first().isFavority)
        }
    }
}