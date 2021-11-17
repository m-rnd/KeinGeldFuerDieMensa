package m_rnd.keingeldfuerdiemensa.datasource.db.implementation

import androidx.room.Database
import androidx.room.RoomDatabase
import m_rnd.keingeldfuerdiemensa.datasource.db.model.DbCanteen

@Database(entities = [
    DbCanteen::class
], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun canteenDao(): CanteenDao
}