package com.erkindilekci.marvelheroesbook.presentation.util

sealed class Screen(val route: String) {
    object ListScreen: Screen("list_screen")
    object DetailScreen: Screen("detail_screen")
}