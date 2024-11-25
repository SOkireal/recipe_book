package com.example.recipebook.presentation.screens.details

import android.content.Context
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
        val recipeId = arguments?.getInt(EXTRA_RECIPE_ID)
        detailsViewModel.onViewCreate(recipeId ?: UNKNOWN_RECIPE_ID)
        Log.d(TAG, "Start DetailsFragment with id $recipeId")
        binding = DetailsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback {
            Log.d("bbb", this.toString())
            detailsViewModel.onBackClick()
        }

        binding.apply {
            detailsListRv.layoutManager = LinearLayoutManager(root.context)
            detailsListRv.adapter = detailsAdapter
            backButtonIv.setOnClickListener {
                detailsViewModel.onBackClick()
            }

            addFavoritesIv.setOnClickListener {
                addFavoritesIv.isVisible = false
                deleteFavoritesIv.isVisible = true
            }

            deleteFavoritesIv.setOnClickListener {
                deleteFavoritesIv.isVisible = false
                addFavoritesIv.isVisible = true
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
    }

    private fun showLoading() {

    }

    private fun showError(error: DetailsFragmentViewState.Error) {

    }

    companion object {
        const val TAG: String = "DetailsFragment"
        const val EXTRA_RECIPE_ID = "EXTRA_RECIPE_ID"
        private const val UNKNOWN_RECIPE_ID = -1
    }
}
