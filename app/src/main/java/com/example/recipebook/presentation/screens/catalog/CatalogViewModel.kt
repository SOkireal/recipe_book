package com.example.recipebook.presentation.screens.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.model.RecipeModel
import com.example.domain.domain.usecase.GetCatalogUseCase
import com.example.recipebook.presentation.navigation.FragmentRouter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatalogViewModel(val getCatalogUseCase: GetCatalogUseCase, val fragmentRouter: FragmentRouter): ViewModel() {
    private val _stateFlow = MutableStateFlow<CatalogFragmentViewState>(CatalogFragmentViewState.Loading)
    val stateFlow = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            val viewState = RecipeModelToCatalogFragmentViewStateMapper.invoke(getCatalogUseCase())
            _stateFlow.emit(viewState)
        }
    }

    fun onRecipeClick(recipe : RecipeModel) {
        fragmentRouter.showDetailsFragment(recipe)
    }
}
