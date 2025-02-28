package com.example.showspotter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.showspotter.presentation.details.DetailsScreen
import com.example.showspotter.presentation.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onItemClick = { titleId ->
                    navController.navigate("details/$titleId")
                }
            )
        }
        composable(
            route = "details/{titleId}",
            arguments = listOf(navArgument("titleId") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailsScreen(
                titleId = backStackEntry.arguments?.getString("titleId") ?: "",
                navController = navController
            )
        }
    }
}
