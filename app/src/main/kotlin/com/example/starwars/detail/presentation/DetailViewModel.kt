package com.example.starwars.detail.presentation

import androidx.lifecycle.MutableLiveData
import com.example.starwars.categories.domain.model.CategoryType
import com.example.starwars.detail.domain.DetailInteractor
import com.example.starwars.detail.domain.model.Detail
import com.example.starwars.util.BaseViewModel
import kotlinx.coroutines.launch

class DetailViewModel(private val interactor: DetailInteractor): BaseViewModel() {

    val detailOb = MutableLiveData<List<Detail>>()
    val detailExceptionOb = MutableLiveData<Exception>()

    fun getDetail(categoryType: String) {
        when (categoryType.uppercase()) {
            "FILMS" -> getFilmsDetail()
            "PEOPLE" -> getPeopleDetail()
            "PLANETS" -> getPlanetsDetail()
            "SPECIES" -> getSpeciesDetail()
            "VEHICLES" -> getVehiclesDetail()
            "STARSHIPS" -> getStarshipsDetail()
        }
    }

    private fun getFilmsDetail() {
        launch {
            try {
                val films = interactor.getFilmsDetail()
                detailOb.value = films
            } catch (ex: Exception) {
                detailExceptionOb.value = ex
            }
        }
    }

    private fun getPeopleDetail() {
        launch {
            try {
                val films = interactor.getPeopleDetail()
                detailOb.value = films
            } catch (ex: Exception) {
                detailExceptionOb.value = ex
            }
        }
    }

    private fun getPlanetsDetail() {
        launch {
            try {
                val films = interactor.getPlanetsDetail()
                detailOb.value = films
            } catch (ex: Exception) {
                detailExceptionOb.value = ex
            }
        }
    }

    private fun getSpeciesDetail() {
        launch {
            try {
                val films = interactor.getSpeciesDetail()
                detailOb.value = films
            } catch (ex: Exception) {
                detailExceptionOb.value = ex
            }
        }
    }

    private fun getVehiclesDetail() {
        launch {
            try {
                val films = interactor.getVehiclesDetail()
                detailOb.value = films
            } catch (ex: Exception) {
                detailExceptionOb.value = ex
            }
        }
    }

    private fun getStarshipsDetail() {
        launch {
            try {
                val films = interactor.getStarshipsDetail()
                detailOb.value = films
            } catch (ex: Exception) {
                detailExceptionOb.value = ex
            }
        }
    }
}