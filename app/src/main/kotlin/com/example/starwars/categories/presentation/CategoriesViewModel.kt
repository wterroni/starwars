package com.example.starwars.categories.presentation

import androidx.lifecycle.MutableLiveData
import com.example.starwars.categories.domain.CategoriesInteractor
import com.example.starwars.categories.domain.model.Category
import com.example.starwars.util.BaseViewModel
import kotlinx.coroutines.launch

class CategoriesViewModel(private val interactor: CategoriesInteractor): BaseViewModel() {

    val categoriesOb = MutableLiveData<Array<Category>>()
    val categoriesExceptionOb = MutableLiveData<Exception>()

    init {
        getCategories()
    }

    private fun getCategories() {
        launch {
            try {
                val categories = interactor.getCategories()
                categoriesOb.value = categories
            } catch (ex: Exception) {
                categoriesExceptionOb.value = ex
            }
        }
    }
}