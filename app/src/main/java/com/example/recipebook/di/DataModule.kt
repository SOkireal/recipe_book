package com.example.recipebook.di

import com.example.data.repository.RecipeRepositoryImpl
import com.example.domain.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(): RecipeRepository {
        return RecipeRepositoryImpl()
    }
}
