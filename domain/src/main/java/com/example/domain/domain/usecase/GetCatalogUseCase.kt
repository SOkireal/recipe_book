package com.example.domain.domain.usecase

import com.example.domain.domain.model.RecipeModel
import com.example.domain.domain.repository.RecipeRepository

class GetCatalogUseCase(
    private val recipeRepository: RecipeRepository,
) : (suspend ()->List<RecipeModel>) {

    override suspend fun invoke(): List<RecipeModel> {
        return recipeRepository.getCatalog()
    }
}
