package com.example.recipe4u.modul

data class RecipeResult(
    var id: Int,
    var title: String,
    var image: String,
    var diet: String = ""
)
