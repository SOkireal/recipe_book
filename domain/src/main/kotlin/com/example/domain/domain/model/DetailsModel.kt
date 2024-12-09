package com.example.domain.domain.model

data class DetailsModel (
    val id: Int,
    val title: String,
    val image: String,
    val servings: Int,
    val readyInMinutes: Int,
    val extendedIngredients: List<String>,
    val steps: List<String>,
    val isFavorite: Boolean,
)
