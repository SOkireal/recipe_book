package com.example.domain.domain.repository

import com.example.domain.domain.model.DetailsModel
import com.example.domain.domain.model.RecipeModel
import com.example.domain.domain.model.SearchRequestModel

interface RecipeRepository {

    suspend fun getCatalog(): List<RecipeModel>
    suspend fun getDetails(recipeId: Int): DetailsModel
    suspend fun getRecipeByName(recipeName: SearchRequestModel): List<RecipeModel>
}
