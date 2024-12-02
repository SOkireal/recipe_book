package com.example.data.mapper

import com.example.domain.domain.model.DetailsModel
import com.example.data.model.DetailsIngredientsDataModel
import com.example.data.model.DetailsStepsDataModel

object DetailsIngredientsDataModelAndDetailsStepsDataModelToDetailsModelMapper: (
    (DetailsIngredientsDataModel, List<DetailsStepsDataModel>, Boolean)->DetailsModel
) {
    override fun invoke(
        ingredientsDM: DetailsIngredientsDataModel,
        stepsDM: List<DetailsStepsDataModel>,
        isFavorite: Boolean
    ): DetailsModel {
        return DetailsModel(
            id = ingredientsDM.id,
            title = ingredientsDM.title,
            image = ingredientsDM.image,
            servings = ingredientsDM.servings,
            readyInMinutes = ingredientsDM.readyInMinutes,
            extendedIngredients = ingredientsDM.extendedIngredients.map { it.original },
            steps = stepsDM.first().steps.map { it.step },
            isFavorite = isFavorite
        )
    }
}
