package com.example.recipe4u.modul

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favorite_database")
data class FavRecipe (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var image: String,
    var recipeId: Int
)