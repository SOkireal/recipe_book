package com.example.recipebook.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.model.RecipeModel
import com.example.domain.domain.usecase.AddFavoriteUseCase
import com.example.domain.domain.usecase.GetDetailsUseCase
import com.example.domain.domain.usecase.RemoveFavoriteUseCase
import com.example.recipebook.presentation.navigation.FragmentRouter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getDetailsUseCase: GetDetailsUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val fragmentRouter: FragmentRouter,
): ViewModel() {

    private lateinit var currentRecipeModel: RecipeModel
    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        DetailsFragmentViewState.Error
        viewModelScope.launch {
            _stateFlow.emit(DetailsFragmentViewState.Error)
        }
    }
    private val _stateFlow = MutableStateFlow<DetailsFragmentViewState>(DetailsFragmentViewState.Loading)
    val stateFlow = _stateFlow.asStateFlow()

    fun onViewCreate(recipeModel: RecipeModel?) {
        if (recipeModel == null) throw IllegalArgumentException("recipeModel is null")
        currentRecipeModel = recipeModel
        viewModelScope.launch(exceptionHandler) {
            val viewState = DetailsModelToDetailsFragmentViewStateMapper
                .invoke(getDetailsUseCase(recipeModel))
            _stateFlow.emit(viewState)
        }
    }

    fun onBackClick() {
        fragmentRouter.back()
    }

    fun onAddFavoriteClick() {
        viewModelScope.launch {
            addFavoriteUseCase(currentRecipeModel)
        }
    }

    fun onRemoveFavoriteClick() {
        viewModelScope.launch {
            removeFavoriteUseCase(currentRecipeModel)
        }
    }
}
