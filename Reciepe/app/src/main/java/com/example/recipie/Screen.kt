package com.example.recipie

import okhttp3.Route

sealed class Screen(val route: String){
    object RecipieScreen:Screen("recipiescreen")
    object DetailScreen:Screen("detailscreen")




}