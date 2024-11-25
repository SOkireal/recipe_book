package com.example.domain.domain.repository

import com.example.domain.domain.model.RecipeModel

interface RecipeRepository {

    suspend fun getCatalog(): List<RecipeModel>
}
