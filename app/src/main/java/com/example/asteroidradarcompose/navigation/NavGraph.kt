package com.example.asteroidradarcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.asteroidradarcompose.details.DetailsScreen
import com.example.asteroidradarcompose.main.MainScreen
import com.example.asteroidradarcompose.main.MainViewModel

@Composable
fun NavGraph(navController: NavHostController, mainViewModel: MainViewModel) {
    NavHost(navController, startDestination = "main") {
        composable("main") { MainScreen(navController, mainViewModel) }
        composable("details/{asteroidId}") { backStackEntry ->
            // DetailsScreen() is a composable that shows the details of the asteroid
            DetailsScreen(asteroidId = backStackEntry.arguments?.getString("asteroidId"))
        }
    }
}