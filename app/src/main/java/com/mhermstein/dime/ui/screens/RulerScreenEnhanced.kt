package com.mhermstein.dime.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
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
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mhermstein.dime.ui.viewmodel.MeasurementViewModel
import kotlin.math.sqrt

@Composable
fun RulerScreenEnhanced(
    navController: NavController,
    viewModel: MeasurementViewModel
) {
    val scale = remember { mutableStateOf(1f) }
    val rotation = remember { mutableStateOf(0f) }

    // placeholder points (pixels); you can later collect by taps on Canvas
    val p1 = remember { mutableStateOf(0f to 0f) }
    val p2 = remember { mutableStateOf(200f to 120f) }

    val distancePx = remember {
        mutableStateOf(
            sqrt(
                ((p2.value.first - p1.value.first) * (p2.value.first - p1.value.first) +
                        (p2.value.second - p1.value.second) * (p2.value.second - p1.value.second)).toDouble()
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("📏 Ruler", style = MaterialTheme.typography.headlineMedium)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .background(Color(0xFFECECEC), shape = MaterialTheme.shapes.medium)
                .pointerInput(Unit) {
                    detectTransformGestures { _, _, zoom, rot ->
                        scale.value = (scale.value * zoom).coerceIn(0.5f, 3f)
                        rotation.value = (rotation.value + rot) % 360f
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Canvas placeholder")
                Text("Scale: ${String.format("%.2f", scale.value)}x")
                Text("Rotation: ${String.format("%.1f", rotation.value)}°")
                Text("Distance: ${String.format("%.2f", distancePx.value)} px")
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.medium)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Scale")
            Slider(value = scale.value, onValueChange = { scale.value = it }, valueRange = 0.5f..3f)

            Text("Rotation")
            Slider(value = rotation.value, onValueChange = { rotation.value = it }, valueRange = 0f..360f)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { navController.popBackStack() }, modifier = Modifier.weight(1f)) { Text("Back") }
            Button(onClick = { /* Save */ }, modifier = Modifier.weight(1f)) { Text("Save") }
        }
    }
}