package com.example.recipebook.presentation.screens.catalog

import com.example.domain.domain.model.RecipeModel

object RecipeModelToCatalogFragmentViewStateMapper: ((List<RecipeModel>?)->CatalogFragmentViewState) {
    override fun invoke(recipeList: List<RecipeModel>?): CatalogFragmentViewState {
        return if (recipeList == null) {
            CatalogFragmentViewState.Error("Error")
        } else {
            CatalogFragmentViewState.Ready(recipeList)
        }
    }
}
