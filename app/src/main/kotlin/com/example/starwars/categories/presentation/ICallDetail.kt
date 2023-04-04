package com.example.starwars.categories.presentation

import com.example.starwars.categories.domain.model.Category

interface IcallDetail {
    fun callDetail(categoriesVO: Category)
}