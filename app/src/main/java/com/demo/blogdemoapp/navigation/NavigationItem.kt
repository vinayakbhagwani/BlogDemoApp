package com.demo.blogdemoapp.navigation

sealed class NavigationItem(val route: String) {

    object Home: NavigationItem("Home")
    object DetailScreen: NavigationItem("details/{blogId}")
}