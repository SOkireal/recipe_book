package com.example.recipebook.presentation.screens.details

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.domain.model.RecipeModel
import com.example.recipebook.adapter.detais_recycler.DetailsAdapter
import com.example.recipebook.app.App
import com.example.recipebook.databinding.DetailsFragmentBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsFragment : Fragment() {

    lateinit var binding: DetailsFragmentBinding
    @Inject
    lateinit var detailsViewModel: DetailsViewModel

    private val detailsAdapter = DetailsAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recipeModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(EXTRA_RECIPE_ID, RecipeModel::class.java)
        } else {
            arguments?.getSerializable(EXTRA_RECIPE_ID) as RecipeModel
        }
        Log.d(TAG, "Start DetailsFragment with id $recipeModel")
        detailsViewModel.onViewCreate(recipeModel)
        binding = DetailsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback {
            Log.d("bbb", this.toString()) // TODO: !!!
            detailsViewModel.onBackClick()
        }

        binding.apply {
            detailsListRv.layoutManager = LinearLayoutManager(root.context)
            detailsListRv.adapter = detailsAdapter
            backButtonIv.setOnClickListener {
                detailsViewModel.onBackClick()
            }

            addFavoritesIv.setOnClickListener {
                showFavoriteButton(true)
                detailsViewModel.onAddFavoriteClick()
            }

            deleteFavoritesIv.setOnClickListener {
                showFavoriteButton(false)
                detailsViewModel.onRemoveFavoriteClick()
            }

            lifecycleScope.launch {
                detailsViewModel.stateFlow.collect { viewState ->
                    Log.d("GGG", viewState.toString())
                    when (viewState) {
                        is DetailsFragmentViewState.Ready -> showReady(viewState)
                        is DetailsFragmentViewState.Loading -> showLoading()
                        is DetailsFragmentViewState.Error -> showError(viewState)
                    }
                }
            }
        }
    }

    private fun showReady(ready: DetailsFragmentViewState.Ready) {
        detailsAdapter.setData(ready.detailsItems)
        showFavoriteButton(ready.isFavorite)
        binding.apply {
            detailsListRv.isVisible = true
            loadingPb.isVisible = false
            errorLoadRecipeList.isVisible = false
        }
    }

    private fun showLoading() {
        binding.apply {
            detailsListRv.isVisible = false
            loadingPb.isVisible = true
            errorLoadRecipeList.isVisible = false
        }
    }

    private fun showError(error: DetailsFragmentViewState.Error) {
        binding.apply {
            detailsListRv.isVisible = false
            loadingPb.isVisible = false
            errorLoadRecipeList.isVisible = true
        }
    }

    private fun showFavoriteButton(show: Boolean) {
        binding.apply {
            addFavoritesIv.isVisible = !show
            deleteFavoritesIv.isVisible = show
        }
    }

    companion object {
        const val TAG: String = "DetailsFragment"
        const val EXTRA_RECIPE_ID = "EXTRA_RECIPE_ID"
    }
}
