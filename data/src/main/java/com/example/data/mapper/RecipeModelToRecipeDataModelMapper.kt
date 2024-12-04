package com.example.data.mapper

import com.example.data.model.RecipeDataModel
import com.example.domain.domain.model.RecipeModel

object RecipeModelToRecipeDataModelMapper: (suspend (RecipeModel)->RecipeDataModel) {
    override suspend fun invoke(recipeModel: RecipeModel): RecipeDataModel {
        return RecipeDataModel(
            id = recipeModel.id,
            title = recipeModel.title,
            image = recipeModel.image,
        )
    }
}
