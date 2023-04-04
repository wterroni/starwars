package com.example.starwars.categories.presentation

import com.example.starwars.categories.domain.data.Category

interface IcallDetail {
    fun callDetail(categoriesVO: Category)
}