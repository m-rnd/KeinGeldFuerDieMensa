package m_rnd.keingeldfuerdiemensa.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m_rnd.keingeldfuerdiemensa.datasource.api.OpenMensaDataSource
import m_rnd.keingeldfuerdiemensa.datasource.api.OpenMensaDataSourceImpl
import m_rnd.keingeldfuerdiemensa.datasource.api.implementation.OpenMensaService
import m_rnd.keingeldfuerdiemensa.datasource.db.DbCanteenDataSource
import m_rnd.keingeldfuerdiemensa.datasource.db.DbCanteenDataSourceImpl
import m_rnd.keingeldfuerdiemensa.datasource.db.implementation.CanteenDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {
    @Provides
    @Singleton
    fun provideApiMealDatasource(openMensaService: OpenMensaService): OpenMensaDataSource = OpenMensaDataSourceImpl(openMensaService)

    @Provides
    @Singleton
    fun provideDbCanteenDatasource(canteenDao: CanteenDao): DbCanteenDataSource = DbCanteenDataSourceImpl(canteenDao)
}