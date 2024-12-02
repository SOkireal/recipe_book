package com.example.recipebook.presentation.screens.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.model.RecipeModel
import com.example.domain.domain.model.SearchRequestModel
import com.example.domain.domain.usecase.GetCatalogUseCase
import com.example.domain.domain.usecase.SearchUseCase
import com.example.recipebook.presentation.navigation.FragmentRouter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatalogViewModel(
    val getCatalogUseCase: GetCatalogUseCase,
    val searchUseCase: SearchUseCase,
    val fragmentRouter: FragmentRouter,
): ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        CatalogFragmentViewState.Error(throwable.toString())
        viewModelScope.launch {
            _stateFlow.emit(CatalogFragmentViewState.Error(throwable.toString()))
        }
    }
    private val _stateFlow = MutableStateFlow<CatalogFragmentViewState>(CatalogFragmentViewState.Loading)
    val stateFlow = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch(exceptionHandler) {
            val viewState = RecipeModelToCatalogFragmentViewStateMapper.invoke(getCatalogUseCase())
            _stateFlow.emit(viewState)
        }
    }

    fun onRecipeClick(recipe : RecipeModel) {
        fragmentRouter.showDetailsFragment(recipe)
    }

    fun onSearchClicked(recipeName: String) {
        viewModelScope.launch {
            _stateFlow.emit(CatalogFragmentViewState.Loading)
            val searchRequestModel = SearchRequestModel(recipeName)
            val viewState = RecipeModelToCatalogFragmentViewStateMapper.invoke(searchUseCase(searchRequestModel))
            _stateFlow.emit(viewState)
        }
    }
}
