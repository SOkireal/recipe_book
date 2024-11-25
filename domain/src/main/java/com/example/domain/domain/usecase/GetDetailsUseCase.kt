package com.example.domain.domain.usecase

import com.example.domain.domain.model.DetailsModel

class GetDetailsUseCase: ((Int)->DetailsModel) {
    override fun invoke(recipeId: Int): DetailsModel {
        return DetailsModel(
            id = 0,
            title = "Borsch",
            image = "https://i.pinimg.com/originals/77/94/5a/77945a6d40ab987b86095d6634410177.jpg",
            servings = 5,
            readyInMinutes = 120,
            extendedIngredients = listOf(
                "Pork meat",
                "Potato",
                "Onion",
                "Beet",
                "Carrot",
                "Cabbage",
                "Tomato paste",
                "Vegetable oil",
                "Greens",
                "Lemon juice",
                "Sour cream",
                "Bay leaf",
                "Garlic",
                "Salt",
                "Sugar",
                "Pepper",
            ),
            steps = listOf(
                "Boil a piece of pork or beef, preferably with rib bones, for 2 hours. Add 1 onion to boost the taste. Water will evaporate, so be sure to add MORE water in the process. Remove the foam from time to time.",
                "Take the meat out of the broth. Filter the broth. Cut the meat into pieces of 2cm and put it back. Throw away the onion – it has already given its taste.",
                "In a separate frying pan, sauté the shredded onion and carrot with sunflower oil or butter. Add freshly chopped tomatoes or canned tomatoes with their juice.",
                "In a separate frying pan, shred beetroot – use already boiled or sauté a raw one. Add something acidic – white vinegar or lemon juice – that helps preserve the deep red color.",
                "Add several peppercorns and 1 bay leaf.",
                "Add sautéed vegetables from both pans.",
                "Add chopped cabbage and sliced sweet pepper, and cook for another 5 min.",
                "If necessary – add water, it needs to cover everything.",
                "Switch off the \uD83D\uDD25. Add chopped greenery, garlic, and salt.",
                "Mix well and let it stay on the stove for 2h to acquire a richer taste.",
            )
        )
    }
}
