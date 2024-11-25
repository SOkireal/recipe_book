package com.example.recipebook.presentation.navigation

import android.util.Log
import androidx.activity.OnBackPressedDispatcher
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.domain.domain.model.RecipeModel
import com.example.recipebook.presentation.screens.catalog.CatalogFragment
import com.example.recipebook.presentation.screens.details.DetailsFragment
import com.example.recipebook.presentation.screens.favorites.FavoritesFragment

class FragmentRouter {
    private lateinit var manager: FragmentManager
    private lateinit var bottomNavigationBarHandler: BottomNavigationBarHandler
    private lateinit var onBackPressedDispatcher: OnBackPressedDispatcher
    private var containerId = -1

    fun setBottomNavigationBarHandler(bottomNavigationBarHandler: BottomNavigationBarHandler) {
        this.bottomNavigationBarHandler = bottomNavigationBarHandler
    }

    fun setFragmentManager(manager: FragmentManager) {
        this.manager = manager
    }

    fun setContainerId(containerId: Int) {
        this.containerId = containerId
    }

    fun showCatalogFragment() {
        val catalogFragment = manager.findFragmentByTag(CatalogFragment.TAG) ?: CatalogFragment()
        showFragment(catalogFragment, CatalogFragment.TAG)
    }

    fun showFavoritesFragment() {
        val favoritesFragment =
            manager.findFragmentByTag(FavoritesFragment.TAG) ?: FavoritesFragment()
        showFragment(favoritesFragment, FavoritesFragment.TAG)
    }

    fun showDetailsFragment(recipeModel: RecipeModel) {
        val detailsFragment = manager.findFragmentByTag(DetailsFragment.TAG) ?: DetailsFragment()
        detailsFragment.arguments = bundleOf(DetailsFragment.EXTRA_RECIPE_ID to recipeModel.id)
        showFragment(detailsFragment, DetailsFragment.TAG)
        bottomNavigationBarHandler.hideBottomNavigationBar()
    }

    fun back() {
        manager.popBackStack()
        bottomNavigationBarHandler.showBottomNavigationBar()
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        manager.beginTransaction()
            .replace(containerId, fragment, tag)
            .addToBackStack(null)
            .commit()
    }
}
