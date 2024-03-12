package com.example.recipe4u.modul

data class RecipeDetail(
    var id: Int,
    var title: String,
    var image: String = "",
    var readyInMinutes: Int = 0,
    var sourceName: String = "",
    var glutenFree: Boolean = true,
    var ketogenic: Boolean = true,
    var vegan: Boolean = true,
    var vegetarian: Boolean = true,
    var diet: String = "",
    var extendedIngredients: List<ExtendedIngredients>
)
