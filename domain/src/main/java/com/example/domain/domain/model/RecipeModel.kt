package com.example.domain.domain.model

import java.io.Serializable

data class RecipeModel (
    val id: Int,
    val title: String,
    val image: String,
): Serializable
