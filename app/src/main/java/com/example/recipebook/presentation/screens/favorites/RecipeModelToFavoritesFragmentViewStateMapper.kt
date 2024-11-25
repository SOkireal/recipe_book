package com.example.recipebook.presentation.screens.favorites

import com.example.domain.domain.model.RecipeModel

object RecipeModelToFavoritesFragmentViewStateMapper: ((List<RecipeModel>?)->FavoritesFragmentViewState ) {
    override fun invoke(recipeList: List<RecipeModel>?): FavoritesFragmentViewState {
        return if (recipeList == null) {
            FavoritesFragmentViewState.Error("Error")
        } else {
            FavoritesFragmentViewState.Ready(recipeList)
        }
    }
}
