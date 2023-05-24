package com.erkindilekci.marvelheroesbook.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.erkindilekci.marvelheroesbook.presentation.detailscreen.HeroDetailScreen
import com.erkindilekci.marvelheroesbook.presentation.listscreen.HeroListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ListScreen.route) {
        composable(Screen.ListScreen.route) {
            HeroListScreen(navController = navController)
        }

        composable(
            route = "${Screen.DetailScreen.route}/{id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.IntType
                }
            )
        ) {
            val id = it.arguments?.getInt("id") ?: 0
            HeroDetailScreen(id = id, navController = navController)
        }
    }
}