package com.example.data.favoritesDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.model.FavoritesDataModel
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Insert
    suspend fun insertRecipe(recipe: FavoriteRecipeDataModel)
    @Query("SELECT * FROM favorites")
    fun getAllFavoriteRecipes(): Flow<List<FavoriteRecipeDataModel>>
}
