package com.example.recipe4u.remote

import com.example.recipe4u.modul.RandomRecipe
import com.example.recipe4u.modul.RecipeDetail
import com.example.recipe4u.modul.SearchResults
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://api.spoonacular.com/recipes/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("complexSearch")
    suspend fun getRecipes(
        @Query("apiKey") key: String,
        @Query("number") number: Int,
        @Query("query") query: String
    ): SearchResults

    @GET("complexSearch")
    suspend fun getCategory(
        @Query("apiKey") key: String,
        @Query("diet") diet: String,
        @Query("number") number: Int
    ): SearchResults

    @GET("{id}/information")
    suspend fun getRecipeDetails(@Path("id") id: Int, @Query("apiKey") key: String): RecipeDetail

    @GET("random")
    suspend fun getRandomRecipe(
        @Query("apiKey") key: String,
        @Query("number") number: Int
    ): RandomRecipe

    @GET("informationBulk")
    suspend fun getInfoBulk(
        @Query("apiKey") key: String,
        @Query("ids") strings: String
    ):RandomRecipe
}

object SpoonacularApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}