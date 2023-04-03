package com.example.starwars.categories.presentation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import com.example.starwars.categories.domain.CategoriesInteractor
import com.example.starwars.categories.domain.data.CategoriesVO
import com.example.starwars.util.BaseViewModel
import kotlinx.coroutines.launch

class CategoriesViewModel(private val interactor: CategoriesInteractor): BaseViewModel() {

    val categoriesOb = MutableLiveData<CategoriesVO>()
    val categoriesExceptionOb = MutableLiveData<Exception>()

    init {

    }

    @VisibleForTesting
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