package com.example.recipebook.presentation.screens.details

import com.example.recipebook.adapter.details_adapter.DetailsItem

sealed class DetailsFragmentViewState {
    data class Ready(
        val detailsItems: List<DetailsItem>,
        val isFavorite: Boolean
    ): DetailsFragmentViewState()
    data object Error: DetailsFragmentViewState()
    data object Loading: DetailsFragmentViewState()
}
