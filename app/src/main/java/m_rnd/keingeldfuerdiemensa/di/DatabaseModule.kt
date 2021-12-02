package m_rnd.keingeldfuerdiemensa.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import m_rnd.keingeldfuerdiemensa.datasource.db.implementation.AppDatabase
import m_rnd.keingeldfuerdiemensa.datasource.db.implementation.CanteenDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideCanteenDao(database: AppDatabase): CanteenDao {
        return database.canteenDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "keingeldfuerdiemensa.db"
        ).build()
    }
}