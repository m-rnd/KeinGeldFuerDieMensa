package m_rnd.keingeldfuerdiemensa.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m_rnd.keingeldfuerdiemensa.datasource.api.ApiMealDataSource
import m_rnd.keingeldfuerdiemensa.datasource.api.ApiMealDataSourceImpl
import m_rnd.keingeldfuerdiemensa.datasource.api.implementation.MensaService
import m_rnd.keingeldfuerdiemensa.datasource.db.DbMensaDataSource
import m_rnd.keingeldfuerdiemensa.datasource.db.DbMensaDataSourceImpl
import m_rnd.keingeldfuerdiemensa.datasource.db.implementation.MensaDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {
    @Provides
    @Singleton
    fun provideApiMealDatasource(mensaService: MensaService): ApiMealDataSource = ApiMealDataSourceImpl(mensaService)

    @Provides
    @Singleton
    fun provideDbMensaDatasource(mensaDao: MensaDao): DbMensaDataSource = DbMensaDataSourceImpl(mensaDao)
}