package com.mhermstein.dime.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mhermstein.dime.ui.screens.CameraScreenEnhanced
import com.mhermstein.dime.ui.screens.GpsScreenEnhanced
import com.mhermstein.dime.ui.screens.HistoryScreenEnhanced
import com.mhermstein.dime.ui.screens.HomeScreen
import com.mhermstein.dime.ui.screens.RulerScreenEnhanced
import com.mhermstein.dime.ui.screens.SettingsScreenEnhanced
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
            CameraScreenEnhanced(navController, viewModel)
        }
        composable("gps") {
            GpsScreenEnhanced(navController, viewModel)
        }
        composable("ruler") {
            RulerScreenEnhanced(navController, viewModel)
        }
        composable("history") {
            HistoryScreenEnhanced(navController, viewModel)
        }
        composable("settings") {
            SettingsScreenEnhanced(navController, viewModel)
        }
    }
}