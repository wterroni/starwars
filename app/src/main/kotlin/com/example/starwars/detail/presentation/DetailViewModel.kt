package com.example.starwars.detail.presentation

import androidx.lifecycle.MutableLiveData
import com.example.starwars.detail.domain.DetailInteractor
import com.example.starwars.detail.domain.model.Detail
import com.example.starwars.util.BaseViewModel
import kotlinx.coroutines.launch

class DetailViewModel(private val interactor: DetailInteractor): BaseViewModel() {

    val detailOb = MutableLiveData<List<Detail>>()
    val detailExceptionOb = MutableLiveData<Exception>()

    fun getFilmsDetail() {
        launch {
            try {
                val films = interactor.getFilmsDetail()
                detailOb.value = films
            } catch (ex: Exception) {
                detailExceptionOb.value = ex
            }
        }
    }

    fun getPeopleDetail() {
        launch {
            try {
                val films = interactor.getPeopleDetail()
                detailOb.value = films
            } catch (ex: Exception) {
                detailExceptionOb.value = ex
            }
        }
    }

    fun getPlanetsDetail() {
        launch {
            try {
                val films = interactor.getPlanetsDetail()
                detailOb.value = films
            } catch (ex: Exception) {
                detailExceptionOb.value = ex
            }
        }
    }

    fun getSpeciesDetail() {
        launch {
            try {
                val films = interactor.getSpeciesDetail()
                detailOb.value = films
            } catch (ex: Exception) {
                detailExceptionOb.value = ex
            }
        }
    }

    fun getVehiclesDetail() {
        launch {
            try {
                val films = interactor.getVehiclesDetail()
                detailOb.value = films
            } catch (ex: Exception) {
                detailExceptionOb.value = ex
            }
        }
    }

    fun getStarshipsDetail() {
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