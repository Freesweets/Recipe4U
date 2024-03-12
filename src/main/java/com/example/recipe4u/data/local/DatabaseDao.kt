package com.example.recipe4u.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipe4u.modul.FavRecipe

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipeItem: FavRecipe)
    @Query("SELECT * FROM favorite_database")
    fun getAll(): LiveData<MutableList<FavRecipe>>
    @Query("DELETE FROM favorite_database")
    suspend fun deleteAll()
    @Delete
    suspend fun deleteById(favRecipe: FavRecipe)
}
