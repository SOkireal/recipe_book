package com.example.recipebook.presentation.screens.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.model.RecipeModel
import com.example.domain.domain.usecase.AddFavoriteUseCase
import com.example.domain.domain.usecase.GetDetailsUseCase
import com.example.recipebook.presentation.navigation.FragmentRouter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getDetailsUseCase: GetDetailsUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val fragmentRouter: FragmentRouter,
): ViewModel() {

    private lateinit var currentRecipeModel: RecipeModel
    private val _stateFlow = MutableStateFlow<DetailsFragmentViewState>(DetailsFragmentViewState.Loading)
    val stateFlow = _stateFlow.asStateFlow()

    fun onViewCreate(recipeModel: RecipeModel?) {
        if (recipeModel == null) throw IllegalArgumentException("recipeModel is null")
        currentRecipeModel = recipeModel
        viewModelScope.launch {
            val viewState = DetailsModelToDetailsFragmentViewStateMapper
                .invoke(getDetailsUseCase(recipeModel))
            _stateFlow.emit(viewState)
        }
    }

    fun onBackClick() {
        fragmentRouter.back()
    }

    fun onAddFavoritesClick() {
        viewModelScope.launch {
            addFavoriteUseCase(currentRecipeModel)
        }
    }

    companion object {
        private const val TAG = "DetailsViewModel"
    }
}
