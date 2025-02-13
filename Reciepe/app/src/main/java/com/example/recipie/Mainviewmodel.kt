package com.example.recipie

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

class Mainviewmodel:ViewModel() {
private val _Categorystate = mutableStateOf(RecipeState())
    val categories :State <RecipeState> = _Categorystate


    init {
        fetch()
    }
   private fun fetch(){
       viewModelScope.launch {
           try {
               val response = recipeService.getcategories()
               _Categorystate.value = _Categorystate.value.copy(
                   list = response.categories,
                   loading = false,
                   error = null
               )


           }catch (e:Exception){
               _Categorystate.value= _Categorystate.value.copy(loading = false, error = "error fetching ${e.message}")


           }
       }

    }

    data class  RecipeState(val loading :Boolean=true,
                            val list:List<Category> = emptyList(),
                            val error:String?=null)

}