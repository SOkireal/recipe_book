package com.example.recipebook.presentation.screens.details

import com.example.recipebook.adapter.detais_recycler.DetailsItem

sealed class DetailsFragmentViewState {
    data class Ready(val detailsItems: List<DetailsItem>): DetailsFragmentViewState()
    data class Error(val text: String): DetailsFragmentViewState()
    data object Loading: DetailsFragmentViewState()
}
