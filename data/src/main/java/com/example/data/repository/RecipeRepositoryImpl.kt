package com.example.data.repository

import com.example.data.mapper.CatalogDataModelToRecipeModelMapper
import com.example.data.mapper.DetailsIngredientsDataModelAndDetailsStepsDataModelToDetailsModelMapper
import com.example.data.mapper.SearchRequestDataModelToSearchRequestModel
import com.example.data.source.RecipeNetworkDataSource
import com.example.domain.domain.model.DetailsModel
import com.example.domain.domain.model.RecipeModel
import com.example.domain.domain.model.SearchRequestModel
import com.example.domain.domain.repository.RecipeRepository

class RecipeRepositoryImpl: RecipeRepository {
    override suspend fun getCatalog(): List<RecipeModel> {
        val catalog = RecipeNetworkDataSource.getCatalog()
        return CatalogDataModelToRecipeModelMapper(catalog)
    }

    override suspend fun getDetails(recipeId: Int): DetailsModel {
        val ingredients = RecipeNetworkDataSource.getIngredients(recipeId)
        val steps = RecipeNetworkDataSource.getSteps(recipeId)
        return DetailsIngredientsDataModelAndDetailsStepsDataModelToDetailsModelMapper(ingredients, steps)
    }

    override suspend fun getRecipeByName(recipeName: SearchRequestModel): List<RecipeModel> {
        val recipeNameDM = SearchRequestDataModelToSearchRequestModel(recipeName)
        val searchRecipeByName = RecipeNetworkDataSource.getRecipeByName(recipeNameDM)
        return CatalogDataModelToRecipeModelMapper(searchRecipeByName)
    }
}
