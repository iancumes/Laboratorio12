package com.example.cameralocationapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.cameralocationapp.screens.CameraScreen
import com.example.cameralocationapp.screens.HomeScreen
import com.example.cameralocationapp.screens.LocationScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home_screen"
    ) {
        composable("home_screen") {
            HomeScreen(navController)
        }
        composable("camera_screen") {
            CameraScreen(navController)
        }
        composable("location_screen") {
            LocationScreen(navController)
        }
    }
}
