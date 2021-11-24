package m_rnd.keingeldfuerdiemensa.datasource.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "canteen")
data class DbCanteen(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val priority: Int,
    val isVisible: Boolean
)