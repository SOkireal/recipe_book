package com.example.domain.domain.usecase

import com.example.domain.domain.model.RecipeModel
import com.example.domain.domain.model.SearchRequestModel
import com.example.domain.domain.repository.RecipeRepository

class SearchUseCase(private val repository: RecipeRepository): suspend (SearchRequestModel)->List<RecipeModel> {
    override suspend fun invoke(recipeName: SearchRequestModel): List<RecipeModel> {
        return repository.getRecipeByName(recipeName)
    }
}
