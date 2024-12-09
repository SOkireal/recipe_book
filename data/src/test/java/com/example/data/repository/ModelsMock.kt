package com.example.data.repository

import com.example.data.favoritesDb.FavoriteRecipeDataModel
import com.example.data.model.CatalogDataModel
import com.example.data.model.DetailsIngredientsDataModel
import com.example.data.model.DetailsStepsDataModel
import com.example.domain.domain.model.RecipeModel
import com.example.domain.domain.model.SearchRequestModel
import kotlinx.coroutines.flow.MutableStateFlow

object ModelsMock {
    val searchRequestModel = SearchRequestModel(
        searchRecipeByName = ""
    )

    val recipeModel = RecipeModel(0, "", "")

    val catalogDataModel = CatalogDataModel(listOf())

    val detailsIngredientsDataModel = DetailsIngredientsDataModel(
        0,
        "",
        "",
        0,
        0,
        arrayListOf()
    )

    val detailsStepsDataModel = listOf(DetailsStepsDataModel(listOf()))

    val favoriteRecipeDataModel =  MutableStateFlow(listOf(FavoriteRecipeDataModel(
                0,
                "",
                "",
            )
        )
    )
}
