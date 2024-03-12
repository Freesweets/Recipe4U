package com.example.recipe4u.modul

data class SearchResults(
    var offset: Int,
    var number: Int,
    var results: List<RecipeResult>
)
