package com.example.data.model


data class DetailsIngredientsDataModel (
  var id: Int,
  var title: String,
  var image: String,
  var servings: Int,
  var readyInMinutes: Int,
  var extendedIngredients: ArrayList<IngredientDataModel> = arrayListOf(),
)
