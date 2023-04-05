package com.example.starwars.categories.presentation

import androidx.lifecycle.MutableLiveData
import com.example.starwars.categories.domain.CategoriesInteractor
import com.example.starwars.categories.domain.model.Category
import com.example.starwars.util.BaseViewModel
import kotlinx.coroutines.launch

class CategoriesViewModel(private val interactor: CategoriesInteractor): BaseViewModel() {

    val categoriesOb = MutableLiveData<Array<Category>>()
    val loadingOB = MutableLiveData<Boolean>()
    val categoriesExceptionOb = MutableLiveData<Exception>()

    init {
        getCategories()
    }

    private fun getCategories() {
        launch {
            try {
                val categories = interactor.getCategories()
                categoriesOb.value = categories
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
}