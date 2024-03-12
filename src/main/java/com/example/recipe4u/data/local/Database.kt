package com.example.recipe4u.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipe4u.modul.FavRecipe


@Database(entities = [FavRecipe::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract val favoriteDao: DatabaseDao

    companion object {
        private lateinit var dbInstance: FavoriteDatabase
        fun getDatabase(context: Context): FavoriteDatabase {
            synchronized(this) {
                if (!this::dbInstance.isInitialized) {
                    dbInstance = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteDatabase::class.java,
                        "favorite_database"
                    ).build()
                }
            }
            return dbInstance
        }
    }
}