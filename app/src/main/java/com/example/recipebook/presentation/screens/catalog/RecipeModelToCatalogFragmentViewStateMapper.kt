package com.example.recipebook.presentation.screens.catalog

import com.example.domain.domain.model.RecipeModel

object RecipeModelToCatalogFragmentViewStateMapper: ((List<RecipeModel>?)->CatalogFragmentViewState) {
    override fun invoke(recipeList: List<RecipeModel>?): CatalogFragmentViewState {
        return when {
            recipeList == null -> CatalogFragmentViewState.Error
            recipeList.isEmpty() -> CatalogFragmentViewState.Empty
            else -> CatalogFragmentViewState.Ready(recipeList)
        }
    }
}
