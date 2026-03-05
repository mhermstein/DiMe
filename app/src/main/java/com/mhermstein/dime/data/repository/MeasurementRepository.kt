import kotlinx.coroutines.flow.Flow

class MeasurementRepository(private val measurementDao: MeasurementDao) {

    suspend fun saveMeasurement(measurement: Measurement) {
        measurementDao.insert(MeasurementEntity.fromDomain(measurement))
    }

    suspend fun updateMeasurement(measurement: Measurement) {
        measurementDao.update(MeasurementEntity.fromDomain(measurement))
    }

    suspend fun deleteMeasurement(measurementId: Long) {
        measurementDao.deleteById(measurementId)
    }

    suspend fun getMeasurementById(measurementId: Long): Measurement? {
        return measurementDao.getById(measurementId)?.toDomain()
    }

    fun getAllMeasurements(): Flow<List<Measurement>> {
        return measurementDao.getAll().map { entities -> entities.map { it.toDomain() } }
    }

    fun getMeasurementsByType(type: String): Flow<List<Measurement>> {
        return measurementDao.getByType(type).map { entities -> entities.map { it.toDomain() } }
    }
}