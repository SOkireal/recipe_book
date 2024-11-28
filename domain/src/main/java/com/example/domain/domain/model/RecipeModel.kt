package com.example.domain.domain.model

import java.io.Serializable

data class RecipeModel (
    var id: Int,
    var title: String,
    var image: String,
): Serializable
