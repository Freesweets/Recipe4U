package com.example.recipe4u.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipe4u.data.local.EinkaufsDatabse
import com.example.recipe4u.data.local.FavoriteDatabase
import com.example.recipe4u.modul.Einkauf
import com.example.recipe4u.modul.FavRecipe
import com.example.recipe4u.modul.RandomRecipe
import com.example.recipe4u.modul.RecipeDetail
import com.example.recipe4u.modul.SearchResults
import com.example.recipe4u.remote.SpoonacularApi

class Repository(
    private val api: SpoonacularApi, val database: FavoriteDatabase, val databaseTwo: EinkaufsDatabse

) {
    private val key = "a85ccbe9f54b4c039fbca3fa3face678"

    private val number = 15


    private val _recipeDetail = MutableLiveData<RecipeDetail>()
    val recipeDetail: LiveData<RecipeDetail>
        get() = _recipeDetail

    private val _searchResults = MutableLiveData<SearchResults>()
    val searchResuls: LiveData<SearchResults>
        get() = _searchResults

    private val _randomRecipe = MutableLiveData<RandomRecipe>()
    val randomrecipe: LiveData<RandomRecipe>
        get() = _randomRecipe

    private val _infoBulk = MutableLiveData<RandomRecipe>()
    val infoBulk: LiveData<RandomRecipe>
        get() = _infoBulk


    var favRecipe: LiveData<MutableList<FavRecipe>> = database.favoriteDao.getAll()
    var einkauf: LiveData<MutableList<Einkauf>> = databaseTwo.einkaufsDao.getAll()


  /*  suspend fun infoBulk(){
        try {
            val result = api.retrofitService.getInfoBulk(key, )
        }
    }
   */

    suspend fun getSearchResults(query: String) {
        try {
            val result = api.retrofitService.getRecipes(key, number, query)
            _searchResults.postValue(result)
            println()
        } catch (e: Exception) {
            Log.e("Repo", "${e.message}")
        }
    }

    suspend fun getCategory(diet: String) {
        try {
            val result = api.retrofitService.getCategory(key, diet, number)
            _searchResults.postValue(result)
        } catch (e: Exception) {
            Log.e("Repo", "${e.message}")
        }
    }

    suspend fun getRecipeDetailView(id: Int) {
        try {
            val result = api.retrofitService.getRecipeDetails(id, key)
            _recipeDetail.postValue(result)
            println()
        } catch (e: Exception) {
            Log.e("Repo", "${e.message}")
        }
    }

    suspend fun getRecipeID(id: Int) {
        try {
            var x = api.retrofitService.getRecipeDetails(id, key)
            _recipeDetail.postValue(x)
        } catch (e: Exception) {
            Log.e("Repo", "${e.message}")
        }
    }

    suspend fun getRandomRecipe() {
        try {
            val result = api.retrofitService.getRandomRecipe(key, number)
            _randomRecipe.value = result
            println()
        } catch (e: Exception) {
            Log.e("Repo", "Fehler beim laden von random recipe ${e.message}")
        }
    }

    suspend fun insertFavRecipe(favRecipe: FavRecipe){
        try {
            database.favoriteDao.insertRecipe(favRecipe)
        }catch (e: Exception){
            Log.e("Repository-Insert-Fehler", e.toString())
        }
    }
    suspend fun deleteFav(){
        try {
            database.favoriteDao.deleteAll()
        } catch (e: Exception){
            Log.e("Repository-Delete-Error", e.toString())
        }
    }
    suspend fun deleteById(currentFavRecipe: FavRecipe){
        try {
            database.favoriteDao.deleteById(currentFavRecipe)
        }catch (e:Exception){
            Log.e("Repository-by-id", e.toString())
        }
    }
    suspend fun deleteEinkauf(){
        try {
            databaseTwo.einkaufsDao.deleteAll()
        } catch (e: Exception){
            Log.e("Repository-Delete-Einkauf", e.toString())
        }
    }
    fun getEinkauf(){
        try {
            einkauf = databaseTwo.einkaufsDao.getAll() as MutableLiveData<MutableList<Einkauf>>
        } catch (e: Exception){
            Log.e("Repository-Einkauf-getAll", e.toString())
        }
    }
    suspend fun insertEinkauf(einkauf: Einkauf){
        try {
            databaseTwo.einkaufsDao.insertInput(einkauf)
        } catch (e: Exception){
            Log.e("Repository-insertEinkauf", e.toString())
        }
    }
    suspend fun deleteEinkaufById(einkauf: Einkauf){
        try {
            databaseTwo.einkaufsDao.deleteById(einkauf)
        } catch (e: Exception){
            Log.e("Repository-delete-byId", e.toString())
        }
    }
    suspend fun clearSearch(){
        _searchResults.postValue(SearchResults(0, 0, emptyList()))
    }

}
