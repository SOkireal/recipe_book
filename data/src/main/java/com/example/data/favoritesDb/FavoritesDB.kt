package com.example.data.favoritesDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteRecipeDataModel::class], version = 1)
internal abstract class FavoritesDB: RoomDatabase() {
    abstract fun getDao(): Dao
    companion object {
        fun getDb(context: Context): FavoritesDB {
            return Room.databaseBuilder(
                context.applicationContext,
                FavoritesDB::class.java,
                "favorites.db"
            ).build()
        }
    }
}
