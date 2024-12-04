package com.example.recipebook.di

import com.example.recipebook.presentation.activities.MainActivity
import com.example.recipebook.presentation.screens.catalog.CatalogFragment
import com.example.recipebook.presentation.screens.details.DetailsFragment
import com.example.recipebook.presentation.screens.favorites.FavoritesFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(catalogFragment: CatalogFragment)
    fun inject(favoritesFragment: FavoritesFragment)
    fun inject(detailsFragment: DetailsFragment)
}
