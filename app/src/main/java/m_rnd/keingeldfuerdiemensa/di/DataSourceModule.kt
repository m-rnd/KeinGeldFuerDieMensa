package m_rnd.keingeldfuerdiemensa.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import m_rnd.keingeldfuerdiemensa.datasource.api.OpenMensaDataSource
import m_rnd.keingeldfuerdiemensa.datasource.api.OpenMensaDataSourceImpl
import m_rnd.keingeldfuerdiemensa.datasource.api.implementation.OpenMensaService
import m_rnd.keingeldfuerdiemensa.datasource.datastore.UserPreferences
import m_rnd.keingeldfuerdiemensa.datasource.datastore.UserPreferencesDataSource
import m_rnd.keingeldfuerdiemensa.datasource.datastore.UserPreferencesDataSourceImpl
import m_rnd.keingeldfuerdiemensa.datasource.datastore.UserPreferencesSerializer
import m_rnd.keingeldfuerdiemensa.datasource.db.DbCanteenDataSource
import m_rnd.keingeldfuerdiemensa.datasource.db.DbCanteenDataSourceImpl
import m_rnd.keingeldfuerdiemensa.datasource.db.implementation.CanteenDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {
    @Provides
    @Singleton
    fun provideApiMealDatasource(openMensaService: OpenMensaService): OpenMensaDataSource =
        OpenMensaDataSourceImpl(openMensaService)

    @Provides
    @Singleton
    fun provideDbCanteenDatasource(canteenDao: CanteenDao): DbCanteenDataSource =
        DbCanteenDataSourceImpl(canteenDao)

    @Provides
    @Singleton
    fun provideUserPreferencesDatasource(userPreferencesDataStore: DataStore<UserPreferences>): UserPreferencesDataSource =
        UserPreferencesDataSourceImpl(userPreferencesDataStore)


    @Provides
    @Singleton
    fun providesUserPreferencesDataStore(
        @ApplicationContext context: Context,
        userPreferencesSerializer: UserPreferencesSerializer,
    ): DataStore<UserPreferences> = DataStoreFactory.create(
        serializer = userPreferencesSerializer,
    ) {
        context.dataStoreFile("user_preferences.pb")
    }
}