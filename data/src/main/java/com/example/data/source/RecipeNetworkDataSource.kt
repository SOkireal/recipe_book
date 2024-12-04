package com.example.data.source

import com.example.data.model.RecipeDataModel
import com.example.data.model.SearchRequestDataModel
import com.example.data.retrofit.RecipeApi
import okhttp3.Interceptor
import okhttp3.Interceptor.Companion.invoke
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeNetworkDataSource {

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
            .addQueryParameter("apiKey", "8874fe1393c74d6fb13360f5d08d0918") // api keys - 8874fe1393c74d6fb13360f5d08d0918, c6635fa1e0594590995d6badc043d882
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

    suspend fun getIngredients(
        recipeDataModel: RecipeDataModel
    ) = recipeApi.getIngredients(recipeDataModel.id)

    suspend fun getSteps(
        recipeDataModel: RecipeDataModel
    ) = recipeApi.getSteps(recipeDataModel.id)

    suspend fun getRecipeByName(
        recipeName: SearchRequestDataModel
    ) = recipeApi.getRecipeByName(recipeName.searchRecipeByName)

    companion object {
        private const val BASE_URL = "https://api.spoonacular.com/"
    }
}
