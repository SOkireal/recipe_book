package com.example.domain.domain.usecase

import com.example.domain.domain.model.DetailsModel
import com.example.domain.domain.model.RecipeModel
import com.example.domain.domain.repository.RecipeRepository

class GetDetailsUseCase(
    private val repository: RecipeRepository
): (suspend (RecipeModel)->DetailsModel) {
    override suspend fun invoke(recipeModel: RecipeModel): DetailsModel {
        return repository.getDetails(recipeModel)
    }
}
