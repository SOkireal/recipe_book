package com.example.data.retrofit

import com.example.data.model.CatalogDataModel
import com.example.data.model.DetailsIngredientsDataModel
import com.example.data.model.DetailsStepsDataModel
import com.example.data.model.SearchRequestDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {
    @GET("recipes/complexSearch")
    suspend fun getCatalog(): CatalogDataModel

    @GET("recipes/{id}/information")
    suspend fun getIngredients(@Path("id") id: Int): DetailsIngredientsDataModel

    @GET("recipes/{id}/analyzedInstructions")
    suspend fun getSteps(@Path("id") id: Int): List<DetailsStepsDataModel>

    @GET("recipes/complexSearch")
    suspend fun getRecipeByName(
        @Query("query") name: String,
        @Query("number") number: Int = 30
    ): CatalogDataModel
}
