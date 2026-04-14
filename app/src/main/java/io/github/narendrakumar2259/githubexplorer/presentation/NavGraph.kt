package io.github.narendrakumar2259.githubexplorer.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.SearchScreen
    ) {
        composable(route = Route.SearchScreen) {
            SearchScreen(navController = navController)
        }

        composable(
            route = "${Route.UserDetail}/{username}",
            arguments = listOf(
                navArgument("username") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            UserDetailScreen(username = username)
        }
    }
}