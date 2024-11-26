package com.example.recipebook.di

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
object AppModule {

    @Provides
    @Singleton
    fun provideRouter(): FragmentRouter {
        return FragmentRouter()
    }

    @Provides
    fun provideCatalogViewModel(getCatalogUseCase: GetCatalogUseCase, searchUseCase: SearchUseCase, fragmentRouter: FragmentRouter): CatalogViewModel {
        return CatalogViewModel(getCatalogUseCase, searchUseCase, fragmentRouter)
    }

    @Provides
    fun provideFavoritesViewModel(getFavoritesUseCase: GetFavoritesUseCase, fragmentRouter: FragmentRouter): FavoritesViewModel {
        return FavoritesViewModel(getFavoritesUseCase, fragmentRouter)
    }

    @Provides
    fun provideDetailsViewModel(getDetailsUseCase: GetDetailsUseCase, fragmentRouter: FragmentRouter): DetailsViewModel {
        return DetailsViewModel(getDetailsUseCase, fragmentRouter)
    }
}
