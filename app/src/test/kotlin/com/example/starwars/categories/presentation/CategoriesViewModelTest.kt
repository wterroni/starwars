package com.example.starwars.categories.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.starwars.categories.domain.CategoriesInteractor
import com.example.starwars.categories.domain.model.Category
import com.example.starwars.categories.mockArrayCategory
import com.example.starwars.categories.mockCategory
import io.mockk.*
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
    private lateinit var viewModel: CategoriesViewModel

    private lateinit var categoriesOb: Observer<List<Category>>
    private lateinit var loadingOB: Observer<Boolean>
    private lateinit var favoriteCategoryListOb: Observer<List<Category>>
    private lateinit var categoriesExceptionOb: Observer<Throwable>

    private val mockError: Throwable = spyk(Throwable())

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        setupViewModel()
    }

    @get:Rule
    val coroutineRule = InstantTaskExecutorRule()

    private fun setupViewModel() {
        viewModel = CategoriesViewModel(interactor)

        categoriesOb = spyk()
        loadingOB = spyk()
        favoriteCategoryListOb = spyk()
        categoriesExceptionOb = spyk()

        viewModel.categoriesOb.observeForever(categoriesOb)
        viewModel.loadingOB.observeForever(loadingOB)
        viewModel.favoriteCategoryListOb.observeForever(favoriteCategoryListOb)
        viewModel.categoriesExceptionOb.observeForever(categoriesExceptionOb)
    }


    @Test
    fun `get category return success`() = runBlocking {
        prepareGetCategories()
        setupViewModel()

        verify {
            categoriesOb.onChanged(any())
            favoriteCategoryListOb.onChanged(any())
            loadingOB.onChanged(false)
        }
    }

    @Test
    fun `get category return error`() = runBlocking {
        every {
            runBlocking {
                interactor.getCategories()
            }
        } throws mockError

        setupViewModel()

        verify {
            categoriesExceptionOb.onChanged(mockError)
            loadingOB.onChanged(false)
        }
    }

    @Test
    fun `retry categories return success`() = runBlocking {
        prepareGetCategories()
        setupViewModel()

        viewModel.retryCategories()

        verifyOrder {
            loadingOB.onChanged(true)
            loadingOB.onChanged(false)
        }

        verify {
            categoriesOb.onChanged(any())
            favoriteCategoryListOb.onChanged(any())
        }
    }

    @Test
    fun `save favorite return success`() = runBlocking {
        every { interactor.saveFavorite(mockCategory) } just Runs
        every { interactor.updateLocalFavorites(mockArrayCategory) } returns mockArrayCategory

        prepareGetCategories()

        setupViewModel()

        viewModel.saveFavorite(mockCategory)

        verify {
            categoriesOb.onChanged(any())
            favoriteCategoryListOb.onChanged(any())
        }
    }

    @Test
    fun `remove favorite return success`() = runBlocking {
        every { interactor.removeFavorite(mockCategory) } just Runs
        every { interactor.updateLocalFavorites(mockArrayCategory) } returns mockArrayCategory

        prepareGetCategories()

        setupViewModel()

        viewModel.removeFavorite(mockCategory)

        verify {
            categoriesOb.onChanged(any())
            favoriteCategoryListOb.onChanged(any())
        }
    }

    private fun prepareGetCategories() {
        val response = ArrayList<Category>()
        response.add(mockCategory)
        every {
            runBlocking {
                interactor.getCategories()
            }
        } returns response
    }

}