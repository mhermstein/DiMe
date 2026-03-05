package com.mhermstein.dime.domain.model

import java.util.Date

// Enumeration for Measurement Type
enum class MeasurementType {
    CAMERA,
    GPS,
    RULER
}

// Enumeration for Measurement Unit
enum class MeasurementUnit {
    LENGTH,
    VOLUME
}

// Data class for Measurement
data class Measurement(
    val id: String,
    val name: String,
    val type: MeasurementType,
    val value: Double,
    val unit: MeasurementUnit,
    val imagePath: String,
    val points: List<Double>,
    val timestamp: Date,
    val description: String,
    val latitude: Double,
    val longitude: Double
) {
    fun toDomainModel(): MeasurementEntity {
        return MeasurementEntity(
            id,
            name,
            type,
            value,
            unit,
            imagePath,
            points,
            timestamp,
            description,
            latitude,
            longitude
        )
    }
}

// Assuming MeasurementEntity has the same fields as Measurement
data class MeasurementEntity(
    val id: String,
    val name: String,
    val type: MeasurementType,
    val value: Double,
    val unit: MeasurementUnit,
    val imagePath: String,
    val points: List<Double>,
    val timestamp: Date,
    val description: String,
    val latitude: Double,
    val longitude: Double
) {
    fun toEntity(): Measurement {
        return Measurement(
            id,
            name,
            type,
            value,
            unit,
            imagePath,
            points,
            timestamp,
            description,
            latitude,
            longitude
        )
    }
}