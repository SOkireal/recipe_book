package com.example.data.retrofit

import com.example.data.model.CatalogDataModel
import retrofit2.http.GET

interface RecipeApi {
    @GET("recipes/complexSearch?apiKey=$API_KEY")
    suspend fun getCatalog(): CatalogDataModel



    companion object {
        const val API_KEY = "8874fe1393c74d6fb13360f5d08d0918"
    }
}
