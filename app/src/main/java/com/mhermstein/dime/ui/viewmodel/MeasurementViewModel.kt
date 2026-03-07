package com.mhermstein.dime.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhermstein.dime.domain.model.Measurement
import com.mhermstein.dime.util.GeoCalculations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.*

class MeasurementViewModel : ViewModel() {

    private val _selectedUnit = MutableStateFlow("meter")
    val selectedUnit: StateFlow<String> = _selectedUnit.asStateFlow()

    private val _darkMode = MutableStateFlow(false)
    val darkMode: StateFlow<Boolean> = _darkMode.asStateFlow()

    private val _isOnlineMode = MutableStateFlow(true)
    val isOnlineMode: StateFlow<Boolean> = _isOnlineMode.asStateFlow()

    private val _allMeasurements = MutableStateFlow<List<Measurement>>(emptyList())
    val allMeasurements: StateFlow<List<Measurement>> = _allMeasurements.asStateFlow()

    fun saveMeasurement(measurement: Measurement) {
        viewModelScope.launch {
            _allMeasurements.value = _allMeasurements.value + measurement
        }
    }

    fun updateMeasurement(measurement: Measurement) {
        viewModelScope.launch {
            _allMeasurements.value = _allMeasurements.value.map {
                if (it.id == measurement.id) measurement else it
            }
        }
    }

    fun deleteMeasurement(measurement: Measurement) {
        viewModelScope.launch {
            _allMeasurements.value = _allMeasurements.value.filter { it.id != measurement.id }
        }
    }

    fun setSelectedUnit(unit: String) {
        _selectedUnit.value = unit
    }

    fun setDarkMode(isDark: Boolean) {
        _darkMode.value = isDark
    }

    fun setOnlineMode(online: Boolean) {
        _isOnlineMode.value = online
    }

    /** Spherical excess approximation for polygon area in m². */
    fun calculatePolygonArea(points: List<Pair<Double, Double>>): Double {
        if (points.size < 3) return 0.0
        val R = 6371000.0
        var area = 0.0
        val n = points.size
        for (i in 0 until n) {
            val j = (i + 1) % n
            val (lat1, lon1) = points[i]
            val (lat2, lon2) = points[j]
            area += Math.toRadians(lon2 - lon1) *
                    (2 + sin(Math.toRadians(lat1)) + sin(Math.toRadians(lat2)))
        }
        return abs(area * R * R / 2.0)
    }

    /** Sum of Haversine distances between consecutive polygon points (closed loop). */
    fun calculatePolygonPerimeter(points: List<Pair<Double, Double>>): Double {
        if (points.size < 2) return 0.0
        val n = points.size
        return (0 until n).sumOf { i ->
            val (lat1, lon1) = points[i]
            val (lat2, lon2) = points[(i + 1) % n]
            GeoCalculations.distanceBetweenPoints(lat1, lon1, lat2, lon2)
        }
    }

    fun convertUnit(value: Double, fromUnit: String, toUnit: String): Double {
        return when {
            fromUnit == "meter" && toUnit == "feet" -> value * 3.28084
            fromUnit == "feet" && toUnit == "meter" -> value / 3.28084
            fromUnit == "inch" && toUnit == "meter" -> value / 39.3701
            fromUnit == "meter" && toUnit == "inch" -> value * 39.3701
            fromUnit == "kilometer" && toUnit == "mile" -> value / 1.60934
            fromUnit == "mile" && toUnit == "kilometer" -> value * 1.60934
            fromUnit == "millimeter" && toUnit == "centimeter" -> value / 10
            fromUnit == "centimeter" && toUnit == "millimeter" -> value * 10
            fromUnit == "yard" && toUnit == "meter" -> value / 1.09361
            fromUnit == "meter" && toUnit == "yard" -> value * 1.09361
            else -> value
        }
    }
}
