package com.example.domain.domain.repository

import com.example.domain.domain.model.DetailsModel
import com.example.domain.domain.model.RecipeModel
import com.example.domain.domain.model.SearchRequestModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getCatalog(): List<RecipeModel>
    suspend fun getDetails(recipeModel: RecipeModel): DetailsModel
    suspend fun getRecipeByName(recipeName: SearchRequestModel): List<RecipeModel>
    suspend fun addFavorite(recipeModel: RecipeModel)
    suspend fun removeFavorite(recipeModel: RecipeModel)
    fun getFavoriteRecipes(): Flow<List<RecipeModel>>
}
