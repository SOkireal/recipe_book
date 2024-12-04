package com.example.data.favoritesDb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface Dao {
    @Query("SELECT * FROM favorites")
    fun getAllFavoriteRecipes(): Flow<List<FavoriteRecipeDataModel>>

    @Insert
    suspend fun insertRecipe(recipe: FavoriteRecipeDataModel)

    @Delete
    suspend fun removeRecipe(recipe: FavoriteRecipeDataModel)

    @Query("SELECT * FROM favorites WHERE id = :recipeId LIMIT 1")
    suspend fun checkFavorite(recipeId: Int): FavoriteRecipeDataModel?
}
