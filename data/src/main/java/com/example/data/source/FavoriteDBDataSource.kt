package com.example.data.source

import android.content.Context
import com.example.data.favoritesDb.FavoriteRecipeDataModel
import com.example.data.favoritesDb.FavoritesDB
import kotlinx.coroutines.flow.Flow

class FavoriteDBDataSource(context: Context) {
    private val favoritesDb = FavoritesDB.getDb(context)

    suspend fun addFavorite(favoriteRecipeDataModel: FavoriteRecipeDataModel) {
        favoritesDb.getDao().insertRecipe(favoriteRecipeDataModel)
    }

    suspend fun removeFavorite(favoriteRecipeDataModel: FavoriteRecipeDataModel) {
        favoritesDb.getDao().removeRecipe(favoriteRecipeDataModel)
    }

    suspend fun checkFavorite(favoriteRecipeDataModel: FavoriteRecipeDataModel): Boolean {
        return favoritesDb.getDao().checkFavorite(favoriteRecipeDataModel.id) != null
    }

    fun getFavoriteRecipes(): Flow<List<FavoriteRecipeDataModel>> {
        return favoritesDb.getDao().getAllFavoriteRecipes()
    }
}
