package com.example.showspotter

import ShowSpotterTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.showspotter.presentation.details.DetailsScreen
import com.example.showspotter.presentation.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShowSpotterTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route,
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable(route = Screen.Home.route) {
                        HomeScreen(onItemClick = { titleId ->
                            if (titleId.toIntOrNull() != null) {
                                navController.navigate(Screen.Details.createRoute(titleId))
                            } else {
                                //  error message
                            }
                        })
                    }
                    composable(
                        route = Screen.Details.route,
                        arguments = listOf(
                            navArgument(Screen.ARG_TITLE_ID) {
                                type = NavType.StringType
                            }
                        )
                    ) { backStackEntry ->
                        val titleId = backStackEntry.arguments?.getString(Screen.ARG_TITLE_ID) ?: ""
                        DetailsScreen(
                            titleId = titleId,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Details : Screen("details/{${Screen.ARG_TITLE_ID}}") {
        fun createRoute(titleId: String) = "details/$titleId"
    }

    companion object {
        const val ARG_TITLE_ID = "titleId"
    }
}
