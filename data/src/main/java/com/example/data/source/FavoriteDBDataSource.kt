package com.example.data.source

import android.content.Context
import android.util.Log
import com.example.data.favoritesDb.FavoriteRecipeDataModel
import com.example.data.favoritesDb.FavoritesDB
import kotlinx.coroutines.flow.Flow

class FavoriteDBDataSource(context: Context) {
    private val favoritesDb = FavoritesDB.getDb(context)
    suspend fun addFavorite(favoriteRecipeDataModel: FavoriteRecipeDataModel) {
        Log.d("???", favoriteRecipeDataModel.toString())
        favoritesDb.getDao().insertRecipe(favoriteRecipeDataModel)
    }

    fun getFavoriteRecipes(): Flow<List<FavoriteRecipeDataModel>> {
        return favoritesDb.getDao().getAllFavoriteRecipes()
    }
}
