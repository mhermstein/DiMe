package com.mhermstein.dime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.mhermstein.dime.ui.navigation.NavGraph
import com.mhermstein.dime.ui.theme.DiMeTheme
import com.mhermstein.dime.ui.viewmodel.MeasurementViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = ViewModelProvider(this).get(MeasurementViewModel::class.java)
            val darkMode by viewModel.darkMode.collectAsState()
            val navController = rememberNavController()

            DiMeTheme(darkTheme = darkMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(navController, viewModel)
                }
            }
        }
    }
}