package com.example.recipe4u.modul

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("einkauf_database")
data class Einkauf (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var text: String
)
