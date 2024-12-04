package com.example.recipebook.presentation.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.model.RecipeModel
import com.example.domain.domain.usecase.GetFavoritesUseCase
import com.example.recipebook.presentation.navigation.FragmentRouter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavoritesViewModel(
    val getFavoritesUseCase: GetFavoritesUseCase,
    val fragmentRouter: FragmentRouter,
): ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        FavoritesFragmentViewState.Error(throwable.toString())
        viewModelScope.launch {
            _stateFlow.emit(FavoritesFragmentViewState.Error(throwable.toString()))
        }
    }
    private val _stateFlow = MutableStateFlow<FavoritesFragmentViewState>(
        FavoritesFragmentViewState.Loading
    )
    val stateFlow = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch(exceptionHandler) {
            getFavoritesUseCase()
                .map { RecipeModelToFavoritesFragmentViewStateMapper(it) }
                .collect { _stateFlow.emit(it) }
        }
    }

    fun onRecipeClick(recipe : RecipeModel) {
        fragmentRouter.showDetailsFragment(recipe)
    }
}
