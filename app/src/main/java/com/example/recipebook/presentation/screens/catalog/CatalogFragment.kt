package com.example.recipebook.presentation.screens.catalog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.domain.model.RecipeModel
import com.example.recipebook.adapter.BrowsingRecipeAdapter
import com.example.recipebook.app.App
import com.example.recipebook.base.RootFragment
import com.example.recipebook.databinding.CatalogFragmentBinding
import com.example.recipebook.presentation.navigation.FragmentRouter
import kotlinx.coroutines.launch
import javax.inject.Inject


class CatalogFragment : RootFragment(), BrowsingRecipeAdapter.ListenerOnClickRecipe {

    @Inject
    lateinit var catalogViewModel: CatalogViewModel
    @Inject
    lateinit var fragmentRouter: FragmentRouter
    lateinit var binding: CatalogFragmentBinding
    private val browsingRecipeAdapter = BrowsingRecipeAdapter(this)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = CatalogFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            recipesListRecyclerView.layoutManager = GridLayoutManager(root.context, 2)
            recipesListRecyclerView.adapter = browsingRecipeAdapter
            searchRecipeSv.setOnQueryTextFocusChangeListener { _, hasFocus ->
                titleLogoTv.isVisible = !hasFocus
            }
        }

        lifecycleScope.launch {
            catalogViewModel.stateFlow.collect { viewState ->
                when (viewState) {
                    is CatalogFragmentViewState.Ready -> showReady(viewState)
                    is CatalogFragmentViewState.Loading -> showLoading()
                    is CatalogFragmentViewState.Error -> showError(viewState)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestFocus()
    }

    override fun onClick(recipe: RecipeModel) {
        catalogViewModel.onRecipeClick(recipe)
    }

    private fun showReady(ready: CatalogFragmentViewState.Ready) {
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

    private fun showError(error: CatalogFragmentViewState.Error) {
        binding.apply {
            loadingPb.isVisible = false
            recipesListRecyclerView.isVisible = false
            errorLoadRecipeList.isVisible = true
        }
    }

    companion object {
        const val TAG: String = "CatalogFragment"
    }
}
