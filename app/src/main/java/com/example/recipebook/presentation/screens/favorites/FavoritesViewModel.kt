package com.example.recipebook.presentation.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.model.RecipeModel
import com.example.domain.domain.usecase.GetFavoritesUseCase
import com.example.recipebook.presentation.navigation.FragmentRouter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(val getFavoritesUseCase: GetFavoritesUseCase, val fragmentRouter: FragmentRouter): ViewModel() {
    private val _stateFlow = MutableStateFlow<FavoritesFragmentViewState>(FavoritesFragmentViewState.Loading)
    val stateFlow = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            val viewState = RecipeModelToFavoritesFragmentViewStateMapper.invoke(getFavoritesUseCase())
            _stateFlow.emit(viewState)
        }
    }

    fun onRecipeClick(recipe : RecipeModel) {
        fragmentRouter.showDetailsFragment(recipe)
    }
}