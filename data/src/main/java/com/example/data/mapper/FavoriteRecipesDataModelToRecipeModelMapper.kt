package com.example.data.mapper

import com.example.data.favoritesDb.FavoriteRecipeDataModel
import com.example.domain.domain.model.RecipeModel

object FavoriteRecipesDataModelToRecipeModelMapper: (
    (List<FavoriteRecipeDataModel>)->List<RecipeModel>
) {
    override fun invoke(favoritesDM: List<FavoriteRecipeDataModel>): List<RecipeModel> {
        return favoritesDM.map { recipeDM ->
            RecipeModel(
                id = recipeDM.id,
                title = recipeDM.title,
                image = recipeDM.image,
            )
        }
    }
}
