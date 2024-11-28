package com.example.recipebook.di

import android.content.Context
import com.example.domain.domain.usecase.AddFavoriteUseCase
import com.example.domain.domain.usecase.GetCatalogUseCase
import com.example.domain.domain.usecase.GetDetailsUseCase
import com.example.domain.domain.usecase.GetFavoritesUseCase
import com.example.domain.domain.usecase.SearchUseCase
import com.example.recipebook.presentation.navigation.FragmentRouter
import com.example.recipebook.presentation.screens.catalog.CatalogViewModel
import com.example.recipebook.presentation.screens.details.DetailsViewModel
import com.example.recipebook.presentation.screens.favorites.FavoritesViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideRouter(): FragmentRouter {
        return FragmentRouter()
    }

    @Provides
    fun provideCatalogViewModel(
        getCatalogUseCase: GetCatalogUseCase,
        searchUseCase: SearchUseCase,
        fragmentRouter: FragmentRouter,
    ): CatalogViewModel {
        return CatalogViewModel(getCatalogUseCase, searchUseCase, fragmentRouter)
    }

    @Provides
    fun provideFavoritesViewModel(
        getFavoritesUseCase: GetFavoritesUseCase,
        fragmentRouter: FragmentRouter,
    ): FavoritesViewModel {
        return FavoritesViewModel(getFavoritesUseCase, fragmentRouter)
    }

    @Provides
    fun provideDetailsViewModel(
        getDetailsUseCase: GetDetailsUseCase,
        addFavoriteUseCase: AddFavoriteUseCase,
        fragmentRouter: FragmentRouter,
    ): DetailsViewModel {
        return DetailsViewModel(getDetailsUseCase, addFavoriteUseCase, fragmentRouter)
    }
}
