package com.example.starwars.categories.presentation

import androidx.lifecycle.MutableLiveData
import com.example.starwars.categories.domain.CategoriesInteractor
import com.example.starwars.categories.domain.model.Category
import com.example.starwars.util.BaseViewModel
import kotlinx.coroutines.launch

class CategoriesViewModel(private val interactor: CategoriesInteractor): BaseViewModel() {

    val categoriesOb = MutableLiveData<List<Category>>()
    val loadingOB = MutableLiveData<Boolean>()
    val favoriteCategoryListOb = MutableLiveData<List<Category>>()
    val categoriesExceptionOb = MutableLiveData<Exception>()

    init {
        getCategories()
    }

    private fun getCategories() {
        launch {
            try {
                val categories = interactor.getCategories()
                categoriesOb.value = categories
                favoriteCategoryListOb.value = categories.filter { char -> char.isFavority }
                loadingOB.value = false
            } catch (ex: Exception) {
                loadingOB.value = false
                categoriesExceptionOb.value = ex
            }
        }
    }

    fun retryCategories() {
        loadingOB.value = true
        getCategories()
    }

    fun saveFavorite(categoryrModel: Category) {
        interactor.saveFavorite(categoryrModel)
        updateLists()
    }

    fun removeFavorite(categoryrModel: Category) {
        interactor.removeFavorite(categoryrModel)
        updateLists()
    }

    private fun updateLists() {
        val updatedList =
            interactor.updateLocalFavorites(
                (categoriesOb.value ?: listOf()) as List<Category>
            )
        if (updatedList.isNotEmpty()) {
            categoriesOb.value = updatedList
            favoriteCategoryListOb.value = updatedList.filter { char -> char.isFavority }
        }else{
            favoriteCategoryListOb.value = interactor.getFavorites()
        }
    }
}