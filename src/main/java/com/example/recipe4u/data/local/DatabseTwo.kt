package com.example.recipe4u.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipe4u.modul.Einkauf

@Database(entities = [Einkauf::class], version = 1)
abstract class EinkaufsDatabse : RoomDatabase() {
    abstract val einkaufsDao: DatabaseDaoTwo

    companion object {
        private lateinit var dbInstance: EinkaufsDatabse
        fun getDatabaseTwo(context: Context): EinkaufsDatabse {
            synchronized(this) {
                if (!this::dbInstance.isInitialized) {
                    dbInstance = Room.databaseBuilder(
                        context.applicationContext,
                        EinkaufsDatabse::class.java,
                        "einkauf_database"
                    ).build()
                }
            }
            return dbInstance
        }
    }
}