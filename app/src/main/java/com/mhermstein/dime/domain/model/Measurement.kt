package com.mhermstein.dime.domain.model

import java.util.Date

enum class MeasurementType { CAMERA, GPS, RULER }

enum class MeasurementUnit { LENGTH, VOLUME }

data class Measurement(
    val id: String = "",
    val name: String = "",
    val type: MeasurementType = MeasurementType.CAMERA,
    val value: Double = 0.0,
    val unit: MeasurementUnit = MeasurementUnit.LENGTH,
    val imagePath: String = "",
    val points: List<Double> = emptyList(),
    val timestamp: Date = Date(),
    val description: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
