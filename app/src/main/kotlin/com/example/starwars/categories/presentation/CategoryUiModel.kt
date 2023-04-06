package com.example.starwars.categories.presentation

import android.content.Context
import com.example.starwars.categories.domain.model.FavoriteCategoryModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

interface CategoryUiModel {
    fun getFavorite(): ArrayList<FavoriteCategoryModel>
    fun saveFavorite(favoriteCategoryModel: FavoriteCategoryModel)
    fun removeFavorite(favoriteCategoryModel: FavoriteCategoryModel)
}

class CategoryUiModelImpl(private val context: Context) : CategoryUiModel {

    override fun saveFavorite(favorite: FavoriteCategoryModel) {
        val favorites = getFavorite()
        if (!favorites.contains(favorite)) {
            favorites.add(favorite)
            SaveList(favorites)
        }
        return
    }

    override fun removeFavorite(favorite: FavoriteCategoryModel) {
        val favorites = getFavorite()
        if (favorites.contains(favorite))
            favorites.remove(favorite)
        SaveList(favorites)
    }

    override fun getFavorite(): ArrayList<FavoriteCategoryModel> {
        val sharedPref = context.getSharedPreferences(FAVORITE_KEY, Context.MODE_PRIVATE)
        val gsonValue = sharedPref?.getString(FAVORITE_KEY, null)
        if (gsonValue != null) {
            val itemType: Type = object : TypeToken<ArrayList<FavoriteCategoryModel>>() {}.type
            return Gson().fromJson(gsonValue, itemType)
        }
        return ArrayList()
    }

    private fun SaveList(favorites: ArrayList<FavoriteCategoryModel>) {
        val sharedPref =
            context.getSharedPreferences(FAVORITE_KEY, Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            val json = Gson().toJson(favorites)
            putString(FAVORITE_KEY, json)
            apply()
        }
    }
    
    companion object {
        const val FAVORITE_KEY = "FAVORITE_KEY"
    }
}