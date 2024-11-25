package com.example.recipebook.presentation.navigation

import androidx.core.view.isVisible
import com.example.recipebook.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationBarHandler(
    private val fragmentRouter: FragmentRouter,
    private val bottomNavigationView: BottomNavigationView,
) {
    init {
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.get_catalog_fragment -> {
                    fragmentRouter.showCatalogFragment()
                }

                R.id.get_favorites_fragment -> {
                    fragmentRouter.showFavoritesFragment()
                }
            }
            true
        }
    }

    fun showBottomNavigationBar() {
        bottomNavigationView.isVisible = true
    }

    fun hideBottomNavigationBar() {
        bottomNavigationView.isVisible = false
    }
}
