package com.example.starwars.categories.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.starwars.categories.domain.CategoriesInteractor
import com.example.starwars.categories.domain.model.Category
import com.example.starwars.categories.mockCategory
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CategoriesViewModelTest {
    private val interactor: CategoriesInteractor = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        setupViewModel()
    }

    @get:Rule
    val coroutineRule = InstantTaskExecutorRule()

    private fun setupViewModel() {
        val response = ArrayList<Category>()
        response.add(mockCategory)
        every {
            runBlocking {
                interactor.getCategories()
            }
        } returns response
    }

    /*
    @Test
    fun `get category return success`() = runBlocking {
        setupViewModel()
        val viewModel = CategoriesViewModel(interactor)

        Assert.assertNotNull(viewModel.categoriesOb.value)
        Assert.assertTrue(viewModel.categoriesOb.value?.size == 2)
        Assert.assertNotNull(viewModel.favoriteCategoryListOb.value)
        Assert.assertTrue(viewModel.favoriteCategoryListOb.value?.size == 1)

    }

     */
}