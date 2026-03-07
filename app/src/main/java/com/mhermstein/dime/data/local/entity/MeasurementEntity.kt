package com.mhermstein.dime.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mhermstein.dime.domain.model.Measurement
import com.mhermstein.dime.domain.model.MeasurementType
import com.mhermstein.dime.domain.model.MeasurementUnit
import java.util.Date

@Entity(tableName = "measurements")
data class MeasurementEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val type: String,           // stored as MeasurementType.name
    val value: Double,
    val unit: String,           // stored as MeasurementUnit.name
    val imagePath: String?,
    val pointCount: Int,        // number of geo-points (full list not stored in DB)
    val timestamp: Long,        // epoch ms – converted by Converters
    val description: String,
    val latitude: Double,
    val longitude: Double
) {
    fun toDomain(): Measurement = Measurement(
        id = id.toString(),
        name = name,
        type = runCatching { MeasurementType.valueOf(type) }.getOrDefault(MeasurementType.CAMERA),
        value = value,
        unit = runCatching { MeasurementUnit.valueOf(unit) }.getOrDefault(MeasurementUnit.LENGTH),
        imagePath = imagePath ?: "",
        points = emptyList(),
        timestamp = Date(timestamp),
        description = description,
        latitude = latitude,
        longitude = longitude
    )

    companion object {
        fun fromDomain(m: Measurement, existingId: Long = 0): MeasurementEntity =
            MeasurementEntity(
                id = existingId,
                name = m.name,
                type = m.type.name,
                value = m.value,
                unit = m.unit.name,
                imagePath = m.imagePath.ifBlank { null },
                pointCount = m.points.size,
                timestamp = m.timestamp.time,
                description = m.description,
                latitude = m.latitude,
                longitude = m.longitude
            )
    }
}
