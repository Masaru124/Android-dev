package com.example.recipie

data class Category(val idCategory:String,
    val strCategory:String,
                    val   strCategoryThumb:String,
    val strCategoryDescription:String

    )

data class Categoriesresponse(val categories : List<Category> )