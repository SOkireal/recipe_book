package com.example.recipebook.presentation.screens.catalog

import com.example.domain.domain.model.RecipeModel

sealed class CatalogFragmentViewState {
    data class Ready(val list: List<RecipeModel>): CatalogFragmentViewState()
    data class Error(val text: String): CatalogFragmentViewState()
    data object Loading: CatalogFragmentViewState()
}
