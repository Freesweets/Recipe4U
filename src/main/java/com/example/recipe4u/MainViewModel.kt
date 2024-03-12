package com.example.recipe4u

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.recipe4u.data.Repository
import com.example.recipe4u.remote.SpoonacularApi
import androidx.lifecycle.viewModelScope
import com.example.recipe4u.data.local.EinkaufsDatabse.Companion.getDatabaseTwo
import com.example.recipe4u.data.local.FavoriteDatabase.Companion.getDatabase
import com.example.recipe4u.modul.Einkauf
import com.example.recipe4u.modul.FavRecipe
import com.example.recipe4u.modul.RecipeDetail
import com.example.recipe4u.modul.RecipeResult
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val databaseTwo = getDatabaseTwo(application)
    private val repository = Repository(SpoonacularApi, database, databaseTwo)

    val recipeSearches = repository.searchResuls
    val recipeDetail = repository.recipeDetail
    val randomRecipe = repository.randomrecipe
    val favRecipe = repository.favRecipe
    val einkauf = repository.einkauf

    private lateinit var _recipeDetail: RecipeDetail

    var currentId = 0
    private lateinit var currentFavRecipe: FavRecipe

    fun setCurrentFavRecipe(favRecipe: FavRecipe){
        currentFavRecipe = favRecipe
    }

    fun getRecipesSearches(query: String) {
        viewModelScope.launch {
            repository.getSearchResults(query)
        }
    }

    fun getRandomRecipe() {
        viewModelScope.launch {
            repository.getRandomRecipe()
        }
    }

    fun getRecipeId(id: Int) {
        viewModelScope.launch {
            repository.getRecipeID(id)
        }
    }

    fun getCategory(diet: String) {
        viewModelScope.launch {
            repository.getCategory(diet)
        }
    }

    fun insertFavRecipe(detail: RecipeDetail) {
        viewModelScope.launch {
            try {
                val recipe = FavRecipe(
                    title = detail.title,
                    image = detail.image,
                    recipeId = currentId
                )
                repository.insertFavRecipe(recipe)
            } catch (e: Exception) {
                Log.e("ViewModel- Insert", "$e")
            }
        }
    }
    fun deleteFavRecipe(){
        viewModelScope.launch {
            try {
                repository.deleteFav()
            } catch (e:Exception){
                Log.e("ViewModel- Delete", "$e")
            }
        }
    }
    fun deleteById(){
        viewModelScope.launch {
            try {
                repository.deleteById(currentFavRecipe)
            } catch (e:Exception){
                Log.e("ViewModel-DeleteId", "$e")
            }
        }
    }
    fun deleteEinkauf(){
        viewModelScope.launch {
            try {
                repository.deleteEinkauf()
            } catch (e:Exception){
                Log.e("ViewModel-Delete-Einkauf", "$e")
            }
        }
    }
    fun insertEinkauf(einkauf: Einkauf){
        viewModelScope.launch {
            try {
                repository.insertEinkauf(einkauf)
            } catch (e: Exception){
                Log.e("Insert-ViewModel-Einkauf", e.toString())
            }
        }
    }
    fun deleteById(einkauf: Einkauf){
        viewModelScope.launch {
            try {
                repository.deleteEinkaufById(einkauf)
            }catch (e: Exception){
                Log.e("DeleteById-ViewModel", e.toString())
            }
        }
    }
    fun clearSearch(){
        viewModelScope.launch {
            try {
                repository.clearSearch()
            } catch (e:Exception){
                Log.e("Search_viewModel", e.toString())
            }
        }
    }
}