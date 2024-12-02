package com.example.recipebook.presentation.screens.details

import com.example.domain.domain.model.DetailsModel
import com.example.recipebook.adapter.detais_recycler.DetailsItem


object DetailsModelToDetailsFragmentViewStateMapper: ((DetailsModel) -> DetailsFragmentViewState) {
    override fun invoke(detailsModel: DetailsModel): DetailsFragmentViewState {
        return if (detailsModel == null) {
            DetailsFragmentViewState.Error("Error")
        } else {

            val detailsItems = mutableListOf<DetailsItem>()

            detailsItems.add(
                DetailsItem.HeaderDetailsItem(
                    detailsModel.title,
                    detailsModel.image,
                    detailsModel.servings,
                    detailsModel.readyInMinutes,
                )
            )

            detailsItems.add(
                DetailsItem.TitleDetailsItem(
                    "Ingredients"
                )
            )

            detailsModel.extendedIngredients.forEach { ingredient ->
                detailsItems.add(
                    DetailsItem.CheckDetailsItem(
                        ingredient
                    )
                )
            }

            detailsItems.add(
                DetailsItem.TitleDetailsItem(
                    "Step by step"
                )
            )

            detailsModel.steps.forEach { step ->
                detailsItems.add(
                    DetailsItem.CheckDetailsItem(
                        step
                    )
                )
            }

            DetailsFragmentViewState.Ready(detailsItems, detailsModel.isFavorite)
        }
    }
}
