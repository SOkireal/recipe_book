package com.example.domain.domain.usecase

import com.example.domain.domain.model.RecipeModel

class AddFavoriteUseCase: ((Int)->RecipeModel) {
    override fun invoke(recipeId: Int): RecipeModel {
        return RecipeModel(
            id = 0,
            title = "Borsch",
            image = "https://i.pinimg.com/originals/77/94/5a/77945a6d40ab987b86095d6634410177.jpg",
        )
    }
}
