package com.example.data.repository

import com.example.data.mapper.CatalogDataModelToRecipeModelMapper
import com.example.data.source.RecipeNetworkDataSource
import com.example.domain.domain.model.RecipeModel
import com.example.domain.domain.repository.RecipeRepository

class RecipeRepositoryImpl: RecipeRepository {
    override suspend fun getCatalog(): List<RecipeModel> {
        val catalog = RecipeNetworkDataSource.getCatalog()
        return CatalogDataModelToRecipeModelMapper(catalog)
    }
}
