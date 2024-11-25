package com.example.data.mapper

import com.example.data.model.CatalogDataModel
import com.example.domain.domain.model.RecipeModel

object CatalogDataModelToRecipeModelMapper: ((CatalogDataModel)->List<RecipeModel>) {
    override fun invoke(catalogDM: CatalogDataModel): List<RecipeModel> {
        return catalogDM.results.map { recipeDM ->
            RecipeModel(
                id = recipeDM.id,
                title = recipeDM.title,
                image = recipeDM.image,
            )
        }
    }
}
