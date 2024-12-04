package com.example.recipebook.presentation.screens.favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.domain.model.RecipeModel
import com.example.recipebook.adapter.catalog_adapter.BrowsingRecipeAdapter
import com.example.recipebook.app.App
import com.example.recipebook.base.RootFragment
import com.example.recipebook.databinding.FavoritesFragmentBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesFragment : RootFragment(), BrowsingRecipeAdapter.ListenerOnClickRecipe {

    @Inject
    lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var binding: FavoritesFragmentBinding
    private val browsingRecipeAdapter = BrowsingRecipeAdapter(this)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavoritesFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            recipesListRecyclerView.layoutManager = GridLayoutManager(root.context, 2)
            recipesListRecyclerView.adapter = browsingRecipeAdapter
        }

        lifecycleScope.launch {
            favoritesViewModel.stateFlow.collect { viewState ->
                when (viewState) {
                    is FavoritesFragmentViewState.Ready -> showReady(viewState)
                    is FavoritesFragmentViewState.Loading -> showLoading()
                    is FavoritesFragmentViewState.Error -> showError()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestFocus()
    }

    override fun onClick(recipe: RecipeModel) {
        favoritesViewModel.onRecipeClick(recipe)
    }

    private fun showReady(ready: FavoritesFragmentViewState.Ready) {
        binding.apply {
            loadingPb.isVisible = false
            recipesListRecyclerView.isVisible = true
            errorLoadRecipeList.isVisible = false
        }
        browsingRecipeAdapter.setData(ready.list)
    }

    private fun showLoading() {
        binding.apply {
            loadingPb.isVisible = true
            recipesListRecyclerView.isVisible = false
            errorLoadRecipeList.isVisible = false
        }
    }

    private fun showError() {
        binding.apply {
            loadingPb.isVisible = false
            recipesListRecyclerView.isVisible = false
            errorLoadRecipeList.isVisible = true
        }
    }

    companion object {
        const val TAG: String = "FavoritesFragment"
    }
}
