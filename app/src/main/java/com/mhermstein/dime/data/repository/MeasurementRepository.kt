package com.mhermstein.dime.data.repository

import com.mhermstein.dime.data.local.dao.MeasurementDao
import com.mhermstein.dime.data.local.entity.MeasurementEntity
import com.mhermstein.dime.domain.model.Measurement
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MeasurementRepository(private val measurementDao: MeasurementDao) {

    suspend fun saveMeasurement(measurement: Measurement) {
        measurementDao.insert(MeasurementEntity.fromDomain(measurement))
    }

    suspend fun updateMeasurement(measurement: Measurement) {
        val id = measurement.id.toLongOrNull() ?: 0L
        measurementDao.update(MeasurementEntity.fromDomain(measurement, id))
    }

    suspend fun deleteMeasurement(measurementId: Long) {
        measurementDao.deleteById(measurementId)
    }

    suspend fun getMeasurementById(measurementId: Long): Measurement? =
        measurementDao.getById(measurementId)?.toDomain()

    fun getAllMeasurements(): Flow<List<Measurement>> =
        measurementDao.getAll().map { entities -> entities.map { it.toDomain() } }

    fun getMeasurementsByType(type: String): Flow<List<Measurement>> =
        measurementDao.getByType(type).map { entities -> entities.map { it.toDomain() } }
}
