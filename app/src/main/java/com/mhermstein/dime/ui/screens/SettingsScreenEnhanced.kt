package com.mhermstein.dime.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mhermstein.dime.ui.viewmodel.MeasurementViewModel

@Composable
fun SettingsScreenEnhanced(
    navController: NavController,
    viewModel: MeasurementViewModel
) {
    val darkMode by viewModel.darkMode.collectAsState()
    val isOnlineMode by viewModel.isOnlineMode.collectAsState()

    val selectedUnit = remember { mutableStateOf("Meter") }
    val showUnitDropdown = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text("⚙️ Settings", style = MaterialTheme.typography.headlineMedium)

        // Dark Mode
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.medium)
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("🌙 Dark Mode")
                Switch(
                    checked = darkMode,
                    onCheckedChange = { viewModel.setDarkMode(it) }
                )
            }
        }

        // Online/Offline Mode for Maps
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.medium)
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("📡 Online Mode (Maps)")
                Switch(
                    checked = isOnlineMode,
                    onCheckedChange = { viewModel.setOnlineMode(it) }
                )
            }
        }

        // Units (simple dropdown using buttons)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.medium)
                .padding(12.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("📐 Measurement Unit")
                Button(
                    onClick = { showUnitDropdown.value = !showUnitDropdown.value },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("${selectedUnit.value} ▼")
                }

                if (showUnitDropdown.value) {
                    listOf("Meter", "Feet", "Kilometer", "Mile", "Centimeter").forEach { unit ->
                        Button(
                            onClick = {
                                selectedUnit.value = unit
                                showUnitDropdown.value = false
                                // Optional: wire to viewModel.setSelectedUnit(...) if you model units there
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(unit)
                        }
                    }
                }
            }
        }

        // Status info
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .background(
                    if (isOnlineMode) MaterialTheme.colorScheme.primaryContainer
                    else MaterialTheme.colorScheme.errorContainer,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(Icons.Filled.Info, contentDescription = null)
                Text(if (isOnlineMode) "Online: OpenStreetMap tiles active" else "Offline: cached tiles only")
            }
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // About
        Text("About", style = MaterialTheme.typography.titleMedium)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.medium)
                .padding(12.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("DiMe – Distance Measurement App", style = MaterialTheme.typography.titleSmall)
                Text("Version 1.0")
                Text("Features:")
                Text("• Camera trigonometry")
                Text("• GPS / OpenStreetMap (online/offline)")
                Text("• Digital ruler (zoom/rotate)")
                Text("• Export (PDF/CSV) + image storage")
            }
        }

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Back")
        }
    }
}