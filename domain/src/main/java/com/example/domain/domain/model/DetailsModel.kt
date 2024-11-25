package com.example.domain.domain.model

data class DetailsModel (
    var id: Int,
    var title: String,
    var image: String,
    var servings: Int,
    var readyInMinutes: Int,
    var extendedIngredients: List<String>,
    var steps: List<String>,
)
