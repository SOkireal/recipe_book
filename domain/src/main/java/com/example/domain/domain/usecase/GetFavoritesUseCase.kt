package com.example.domain.domain.usecase

import com.example.domain.domain.model.RecipeModel

class GetFavoritesUseCase: (()->List<RecipeModel>) {
    override fun invoke(): List<RecipeModel> {
        return listOf(
            RecipeModel(
                id = 0,
                title = "BorschF",
                image = "https://i.pinimg.com/originals/77/94/5a/77945a6d40ab987b86095d6634410177.jpg",
            ),
        )
    }
}
