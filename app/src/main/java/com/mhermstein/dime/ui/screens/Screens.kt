package com.mhermstein.dime.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mhermstein.dime.ui.viewmodel.MeasurementViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: MeasurementViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to DiMe")
        Button(onClick = { navController.navigate("camera") }) {
            Text("📷 Camera")
        }
        Button(onClick = { navController.navigate("gps") }) {
            Text("📍 GPS")
        }
        Button(onClick = { navController.navigate("ruler") }) {
            Text("📏 Ruler")
        }
        Button(onClick = { navController.navigate("history") }) {
            Text("📜 History")
        }
        Button(onClick = { navController.navigate("settings") }) {
            Text("⚙️ Settings")
        }
    }
}

@Composable
fun CameraScreen(navController: NavController, viewModel: MeasurementViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Camera Measurement")
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}

@Composable
fun GpsScreen(navController: NavController, viewModel: MeasurementViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("GPS Measurement")
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}

@Composable
fun RulerScreen(navController: NavController, viewModel: MeasurementViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Ruler Measurement")
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}

@Composable
fun HistoryScreen(navController: NavController, viewModel: MeasurementViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("History")
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}

@Composable
fun SettingsScreen(navController: NavController, viewModel: MeasurementViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Settings")
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}