package com.example.data.favoritesDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteRecipeDataModel(
    @PrimaryKey
    var id: Int,
    var title: String,
    var image: String,
)
