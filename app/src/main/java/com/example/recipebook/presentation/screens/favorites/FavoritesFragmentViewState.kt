package com.example.recipebook.presentation.screens.favorites

import com.example.domain.domain.model.RecipeModel

sealed class FavoritesFragmentViewState {
    data class Ready(val list: List<RecipeModel>): FavoritesFragmentViewState()
    data class Error(val text: String): FavoritesFragmentViewState()
    data object Loading: FavoritesFragmentViewState()
}
