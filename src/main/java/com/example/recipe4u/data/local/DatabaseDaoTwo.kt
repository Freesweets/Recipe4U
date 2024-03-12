package com.example.recipe4u.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipe4u.modul.Einkauf

@Dao
interface DatabaseDaoTwo {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInput(inputText: Einkauf)

    @Query("SELECT * FROM einkauf_database")
    fun getAll(): LiveData<MutableList<Einkauf>>

    @Query("DELETE FROM einkauf_database WHERE id")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteById(inputText: Einkauf)

}