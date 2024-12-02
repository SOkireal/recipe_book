package com.example.recipebook.di

import com.example.domain.domain.repository.RecipeRepository
import com.example.domain.domain.usecase.AddFavoriteUseCase
import com.example.domain.domain.usecase.GetCatalogUseCase
import com.example.domain.domain.usecase.GetDetailsUseCase
import com.example.domain.domain.usecase.GetFavoritesUseCase
import com.example.domain.domain.usecase.RemoveFavoriteUseCase
import com.example.domain.domain.usecase.SearchUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideGetCatalogUseCase(recipeRepository: RecipeRepository): GetCatalogUseCase {
        return GetCatalogUseCase(recipeRepository)
    }

    @Provides
    fun provideGetFavoritesUseCase(recipeRepository: RecipeRepository): GetFavoritesUseCase {
        return GetFavoritesUseCase(recipeRepository)
    }

    @Provides
    fun provideGetDetailsUseCase(recipeRepository: RecipeRepository): GetDetailsUseCase {
        return GetDetailsUseCase(recipeRepository)
    }

    @Provides
    fun provideSearchUseCase(recipeRepository: RecipeRepository): SearchUseCase {
        return SearchUseCase(recipeRepository)
    }

    @Provides
    fun provideAddFavoriteUseCase(recipeRepository: RecipeRepository): AddFavoriteUseCase {
        return AddFavoriteUseCase(recipeRepository)
    }

    @Provides
    fun provideRemoveFavoriteUseCase(recipeRepository: RecipeRepository): RemoveFavoriteUseCase {
        return RemoveFavoriteUseCase(recipeRepository)
    }
}
