package com.example.data.model

data class DetailsIngredientsDataModel (
  val id: Int,
  val title: String,
  val image: String,
  val servings: Int,
  val readyInMinutes: Int,
  val extendedIngredients: ArrayList<IngredientDataModel> = arrayListOf(),
)
