package com.example.data.source

import com.example.data.model.SearchRequestDataModel
import com.example.data.retrofit.RecipeApi
import com.example.domain.domain.model.SearchRequestModel
import okhttp3.Interceptor
import okhttp3.Interceptor.Companion.invoke
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RecipeNetworkDataSource {

    private const val BASE_URL = "https://api.spoonacular.com/"

    private val loggingInterceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val baseInterceptor: Interceptor = invoke { chain ->
        val newUrl = chain
            .request()
            .url
            .newBuilder()
            .addQueryParameter("apiKey", "c6635fa1e0594590995d6badc043d882")
            .build()

        val request = chain
            .request()
            .newBuilder()
            .url(newUrl)
            .build()

        return@invoke chain.proceed(request)
    }

    private val client: OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(baseInterceptor)
        .build()

    private val recipeApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(RecipeApi::class.java)

    suspend fun getCatalog() = recipeApi.getCatalog()
    suspend fun getIngredients(id: Int) = recipeApi.getIngredients(id)
    suspend fun getSteps(id: Int) = recipeApi.getSteps(id)
    suspend fun getRecipeByName(recipeName: SearchRequestDataModel) = recipeApi.getRecipeByName(recipeName.searchRecipeByName)
}
