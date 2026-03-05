import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "measurements")
data class MeasurementEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val type: String,
    val value: Double,
    val unit: String,
    val imagePath: String?,
    val points: Int,
    val timestamp: Date,
    val description: String,
    val latitude: Double,
    val longitude: Double
)