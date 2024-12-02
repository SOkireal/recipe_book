package com.example.data.repository

import android.util.Log
import com.example.data.mapper.CatalogDataModelToRecipeModelMapper
import com.example.data.mapper.DetailsIngredientsDataModelAndDetailsStepsDataModelToDetailsModelMapper
import com.example.data.mapper.FavoriteRecipesDataModelToRecipeModelMapper
import com.example.data.mapper.RecipeModelToFavoriteRecipeDataModelMapper
import com.example.data.mapper.RecipeModelToRecipeDataModelMapper
import com.example.data.mapper.SearchRequestDataModelToSearchRequestModel
import com.example.data.source.FavoriteDBDataSource
import com.example.data.source.RecipeNetworkDataSource
import com.example.domain.domain.model.DetailsModel
import com.example.domain.domain.model.RecipeModel
import com.example.domain.domain.model.SearchRequestModel
import com.example.domain.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipeRepositoryImpl(
    private val recipeNetworkDataSource: RecipeNetworkDataSource,
    private val favoriteDBDataSource: FavoriteDBDataSource,
): RecipeRepository {

    override suspend fun getCatalog(): List<RecipeModel> {
        val catalog = recipeNetworkDataSource.getCatalog()
        return CatalogDataModelToRecipeModelMapper(catalog)
    }

    override suspend fun getDetails(recipeModel: RecipeModel): DetailsModel {
        val recipeDataModel = RecipeModelToRecipeDataModelMapper(recipeModel)
        val favoriteRecipeDataModel = RecipeModelToFavoriteRecipeDataModelMapper(recipeModel)
        val ingredients = recipeNetworkDataSource.getIngredients(recipeDataModel)
        val steps = recipeNetworkDataSource.getSteps(recipeDataModel)
        val isFavorite = favoriteDBDataSource.checkFavorite(favoriteRecipeDataModel)
        Log.d("###", isFavorite.toString())
        return DetailsIngredientsDataModelAndDetailsStepsDataModelToDetailsModelMapper(ingredients, steps, isFavorite)
    }

    override suspend fun getRecipeByName(recipeName: SearchRequestModel): List<RecipeModel> {
        val recipeNameDM = SearchRequestDataModelToSearchRequestModel(recipeName)
        val searchRecipeByName = recipeNetworkDataSource.getRecipeByName(recipeNameDM)
        return CatalogDataModelToRecipeModelMapper(searchRecipeByName)
    }

    override suspend fun addFavorite(recipeModel: RecipeModel) {
        val favoriteRecipeDataModel = RecipeModelToFavoriteRecipeDataModelMapper(recipeModel)
        favoriteDBDataSource.addFavorite(favoriteRecipeDataModel)
    }

    override suspend fun removeFavorite(recipeModel: RecipeModel) {
        val favoriteRecipeDataModel = RecipeModelToFavoriteRecipeDataModelMapper(recipeModel)
        favoriteDBDataSource.removeFavorite(favoriteRecipeDataModel)
    }

    override fun getFavoriteRecipes(): Flow<List<RecipeModel>> {
        val favorites = favoriteDBDataSource.getFavoriteRecipes()
        return favorites.map { FavoriteRecipesDataModelToRecipeModelMapper(it) }
    }
}
