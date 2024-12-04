package com.example.data.mapper

import com.example.data.favoritesDb.FavoriteRecipeDataModel
import com.example.domain.domain.model.RecipeModel

object RecipeModelToFavoriteRecipeDataModelMapper: ((RecipeModel)-> FavoriteRecipeDataModel) {
    override fun invoke(recipeModel: RecipeModel): FavoriteRecipeDataModel {
        return FavoriteRecipeDataModel(
            id = recipeModel.id,
            title = recipeModel.title,
            image = recipeModel.image,
        )
    }
}
