package com.example.recipie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipieApp(navController: NavHostController) {
    val recipieViewModel: Mainviewmodel = viewModel()
    val viewstate by recipieViewModel.categories

    // Define the NavHost to handle navigation
    NavHost(navController = navController, startDestination = Screen.RecipieScreen.route) {

        // Define the recipe screen
        composable(route = Screen.RecipieScreen.route) {
            RecipieScreen(viewstate = viewstate, navigatetodetail = { category ->
                // Pass category to the detail screen using the savedStateHandle
                navController.currentBackStackEntry?.savedStateHandle?.set("cat", category)
                navController.navigate(Screen.DetailScreen.route)
            })
        }

        // Define the detail screen
        composable(route = Screen.DetailScreen.route) {
            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")
                ?: Category("", "", "", "")
            CategoryDetailScreen(Category=category)
        }
    }
}
