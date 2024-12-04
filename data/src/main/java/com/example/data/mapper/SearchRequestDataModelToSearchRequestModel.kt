package com.example.data.mapper

import com.example.data.model.SearchRequestDataModel
import com.example.domain.domain.model.SearchRequestModel

object SearchRequestDataModelToSearchRequestModel: (
    suspend (SearchRequestModel)->SearchRequestDataModel
) {
    override suspend fun invoke(request: SearchRequestModel): SearchRequestDataModel {
        return SearchRequestDataModel(
            searchRecipeByName = request.searchRecipeByName
        )
    }
}
