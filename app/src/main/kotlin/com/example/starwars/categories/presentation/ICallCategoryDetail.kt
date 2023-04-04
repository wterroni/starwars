package com.example.starwars.categories.presentation

import com.example.starwars.categories.domain.model.Category

interface ICallCategoryDetail {
    fun callCategoryDetail(categoriesVO: Category)
}