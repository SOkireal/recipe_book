package com.example.recipebook.di

import android.app.Application
import android.content.Context
import com.example.data.repository.RecipeRepositoryImpl
import com.example.data.source.FavoriteDBDataSource
import com.example.data.source.RecipeNetworkDataSource
import com.example.domain.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        favoriteDBDataSource: FavoriteDBDataSource,
        recipeNetworkDataSource: RecipeNetworkDataSource
    ): RecipeRepository {
        return RecipeRepositoryImpl(recipeNetworkDataSource, favoriteDBDataSource)
    }

    @Provides
    fun provideFavoriteDBDataSource(context: Context): FavoriteDBDataSource {
        return FavoriteDBDataSource(context)
    }

    @Provides
    fun provideRecipeNetworkDataSource(): RecipeNetworkDataSource {
        return RecipeNetworkDataSource()
    }
}
