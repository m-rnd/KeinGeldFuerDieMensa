package m_rnd.keingeldfuerdiemensa.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m_rnd.keingeldfuerdiemensa.datasource.api.OpenMensaDataSource
import m_rnd.keingeldfuerdiemensa.datasource.datastore.UserPreferencesDataSource
import m_rnd.keingeldfuerdiemensa.datasource.db.DbCanteenDataSource
import m_rnd.keingeldfuerdiemensa.repository.CanteenRepository
import m_rnd.keingeldfuerdiemensa.repository.CanteenRepositoryImpl
import m_rnd.keingeldfuerdiemensa.repository.UserPreferenceRepository
import m_rnd.keingeldfuerdiemensa.repository.UserPreferenceRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCanteenRepository(
        openMensaDataSource: OpenMensaDataSource,
        dbCanteenDataSource: DbCanteenDataSource,
    ): CanteenRepository = CanteenRepositoryImpl(
        openMensaDataSource = openMensaDataSource,
        dbCanteenDataSource = dbCanteenDataSource
    )

    @Provides
    @Singleton
    fun provideUserPreferenceRepository(
        userPreferencesDataSource: UserPreferencesDataSource,
    ): UserPreferenceRepository = UserPreferenceRepositoryImpl(
        userPreferencesDataSource = userPreferencesDataSource
    )
}