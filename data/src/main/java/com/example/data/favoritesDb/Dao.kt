package com.example.data.favoritesDb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Insert
    suspend fun insertRecipe(recipe: FavoriteRecipeDataModel)

    @Query("SELECT * FROM favorites")
    fun getAllFavoriteRecipes(): Flow<List<FavoriteRecipeDataModel>>

    @Delete
    suspend fun removeRecipe(recipe: FavoriteRecipeDataModel)

    @Query("SELECT * FROM favorites WHERE id = :recipeId LIMIT 1")
    suspend fun checkFavorite(recipeId: Int): FavoriteRecipeDataModel?
}
