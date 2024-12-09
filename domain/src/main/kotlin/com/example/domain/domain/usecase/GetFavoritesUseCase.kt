package com.example.domain.domain.usecase

import com.example.domain.domain.model.RecipeModel
import com.example.domain.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritesUseCase(
    private val recipeRepository: RecipeRepository
): (()->Flow<List<RecipeModel>>) {
    override fun invoke(): Flow<List<RecipeModel>> {
        return recipeRepository.getFavoriteRecipes()
    }
}
