package com.example.data.favoritesDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteRecipeDataModel(
    @PrimaryKey
    val id: Int,
    val title: String,
    val image: String,
)
