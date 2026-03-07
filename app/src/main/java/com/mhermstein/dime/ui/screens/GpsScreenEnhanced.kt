package com.mhermstein.dime.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mhermstein.dime.ui.viewmodel.MeasurementViewModel

/**
 * Note: This is a UI placeholder that hooks into your existing ViewModel computations.
 * Actual OSM map rendering should be done with osmdroid MapView via AndroidView (next iteration).
 */
@Composable
fun GpsScreenEnhanced(
    navController: NavController,
    viewModel: MeasurementViewModel
) {
    val isOnlineMode by viewModel.isOnlineMode.collectAsState()

    // mock polygon points – replace with points collected from map taps
    val polygonPoints = remember { mutableStateOf(listOf<Pair<Double, Double>>()) }
    val area = remember { mutableStateOf(0.0) }
    val perimeter = remember { mutableStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("📍 GPS Measurement", style = MaterialTheme.typography.headlineMedium)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .background(
                    if (isOnlineMode) Color(0xFF90EE90) else Color(0xFFFFA500),
                    shape = MaterialTheme.shapes.medium
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    if (isOnlineMode) "🗺️ OpenStreetMap (Online)" else "🗺️ OpenStreetMap (Offline Cache)",
                    color = Color.White
                )
                Text("MapView integration goes here", color = Color.White)
                Text("Tap to add points, draw polygon/route", color = Color.White)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.medium)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Points: ${polygonPoints.value.size}")

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        // demo: add a dummy point
                        val next = polygonPoints.value + (52.52 to 13.405) // Berlin
                        polygonPoints.value = next
                    },
                    modifier = Modifier.weight(1f)
                ) { Text("Add Point") }

                Button(
                    onClick = { polygonPoints.value = emptyList(); area.value = 0.0; perimeter.value = 0.0 },
                    modifier = Modifier.weight(1f)
                ) { Text("Clear") }
            }

            Button(
                onClick = {
                    if (polygonPoints.value.size >= 3) {
                        area.value = viewModel.calculatePolygonArea(polygonPoints.value)
                        perimeter.value = viewModel.calculatePolygonPerimeter(polygonPoints.value)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Calculate Area & Perimeter") }

            if (area.value > 0.0) {
                Text("📐 Area: ${String.format("%.2f", area.value)} m²")
            }
            if (perimeter.value > 0.0) {
                Text("📏 Perimeter: ${String.format("%.2f", perimeter.value)} m")
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { navController.popBackStack() }, modifier = Modifier.weight(1f)) { Text("Back") }
            Button(onClick = { /* Save measurement */ }, modifier = Modifier.weight(1f)) { Text("Save") }
        }
    }
}