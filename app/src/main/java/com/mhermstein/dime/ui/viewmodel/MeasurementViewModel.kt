import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.*

class MeasurementViewModel : ViewModel() {
    private val _selectedUnit = MutableStateFlow<String>("meter")
    val selectedUnit: StateFlow<String> = _selectedUnit.asStateFlow()

    private val _darkMode = MutableStateFlow<Boolean>(false)
    val darkMode: StateFlow<Boolean> = _darkMode.asStateFlow()

    private val _allMeasurements = MutableStateFlow<List<Measurement>>(emptyList())
    val allMeasurements: StateFlow<List<Measurement>> = _allMeasurements.asStateFlow()

    fun saveMeasurement(measurement: Measurement) {
        viewModelScope.launch {
            // Logic to save measurement
            _allMeasurements.value = _allMeasurements.value + measurement
        }
    }

    fun updateMeasurement(measurement: Measurement) {
        viewModelScope.launch {
            // Logic to update measurement
            _allMeasurements.value = _allMeasurements.value.map { if (it.id == measurement.id) measurement else it }
        }
    }

    fun deleteMeasurement(measurement: Measurement) {
        viewModelScope.launch {
            // Logic to delete measurement
            _allMeasurements.value = _allMeasurements.value.filter { it.id != measurement.id }
        }
    }

    fun setSelectedUnit(unit: String) {
        _selectedUnit.value = unit
    }

    fun setDarkMode(isDark: Boolean) {
        _darkMode.value = isDark
    }

    fun convertUnit(value: Double, fromUnit: String, toUnit: String): Double {
        // Conversion logic
        // Add logic for each conversion unit based on the provided units
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
            // Add more conversions as necessary
            else -> value // No conversion
        }
    }
}

data class Measurement(val id: Int, val value: Double, val unit: String)