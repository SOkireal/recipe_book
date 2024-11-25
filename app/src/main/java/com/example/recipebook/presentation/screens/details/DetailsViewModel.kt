package com.example.recipebook.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.usecase.AddFavoriteUseCase
import com.example.domain.domain.usecase.GetDetailsUseCase
import com.example.recipebook.presentation.navigation.FragmentRouter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    val getDetailsUseCase: GetDetailsUseCase,
    private val fragmentRouter: FragmentRouter
): ViewModel() {

    private val _stateFlow = MutableStateFlow<DetailsFragmentViewState>(DetailsFragmentViewState.Loading)
    val stateFlow = _stateFlow.asStateFlow()

    fun onViewCreate(recipeId: Int) {
        viewModelScope.launch {
            val viewState = DetailsModelToDetailsFragmentViewStateMapper
                .invoke(getDetailsUseCase(recipeId))
            _stateFlow.emit(viewState)
        }
    }

    fun onBackClick() {
        fragmentRouter.back()
    }

    fun onAddFavoritesClick() {
        //TODO: add favorites
    }
}
