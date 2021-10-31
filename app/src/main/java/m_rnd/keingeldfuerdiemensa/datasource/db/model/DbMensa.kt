package m_rnd.keingeldfuerdiemensa.datasource.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mensa")
data class DbMensa(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
)