package com.example.recipie

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.rememberAsyncImagePainter

@Composable
fun RecipieScreen(modifier: Modifier= Modifier,viewstate:Mainviewmodel.RecipeState , navigatetodetail:(Category)->Unit){
    val recipieViewModel:Mainviewmodel= viewModel()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewstate.loading -> {
                CircularProgressIndicator(modifier.align((Alignment.Center)))
            }
            viewstate.error!=null ->{
                Text("Error Occurred")
            }
            else->{
                Categoryscreen(categories = viewstate.list,navigatetodetail)

            }
        }
    }
}

@Composable
fun Categoryscreen(categories : List<Category>,navigatetodetail:(Category)->Unit){
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(categories){
            category->
            Categoryitem(category,navigatetodetail)
        }


    }
}


@Composable
fun Categoryitem(Category:Category,navigatetodetail:(Category)->Unit)

{
    Column (modifier = Modifier.padding(8.dp ).fillMaxSize().clickable { navigatetodetail(Category) }, horizontalAlignment = Alignment.CenterHorizontally ){
        Image(
            painter = rememberAsyncImagePainter(Category.strCategoryThumb), contentDescription = null, modifier = Modifier.fillMaxSize().aspectRatio(1f)
        )

        Text(
            text = Category.strCategory,
            color = Color.Gray,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )


    }
}