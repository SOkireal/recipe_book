package com.example.domain.domain.usecase

import com.example.domain.domain.model.RecipeModel
import com.example.domain.domain.repository.RecipeRepository

class AddFavoriteUseCase(
    private val repository: RecipeRepository
): (suspend (RecipeModel)->Unit) {
    override suspend fun invoke(recipeModel: RecipeModel) {
        repository.addFavorite(recipeModel)
    }
}
