package com.mhermstein.dime.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mhermstein.dime.ui.screens.CameraScreen
import com.mhermstein.dime.ui.screens.GpsScreen
import com.mhermstein.dime.ui.screens.HistoryScreen
import com.mhermstein.dime.ui.screens.HomeScreen
import com.mhermstein.dime.ui.screens.RulerScreen
import com.mhermstein.dime.ui.screens.SettingsScreen
import com.mhermstein.dime.ui.viewmodel.MeasurementViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: MeasurementViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController, viewModel)
        }
        composable("camera") {
            CameraScreen(navController, viewModel)
        }
        composable("gps") {
            GpsScreen(navController, viewModel)
        }
        composable("ruler") {
            RulerScreen(navController, viewModel)
        }
        composable("history") {
            HistoryScreen(navController, viewModel)
        }
        composable("settings") {
            SettingsScreen(navController, viewModel)
        }
    }
}