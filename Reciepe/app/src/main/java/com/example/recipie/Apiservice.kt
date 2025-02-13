package com.example.recipie

import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit= Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create()).build()

val  recipeService = retrofit.create(Apiservice::class.java)

interface Apiservice{
    @GET("categories.php")
    suspend fun getcategories():Categoriesresponse
}