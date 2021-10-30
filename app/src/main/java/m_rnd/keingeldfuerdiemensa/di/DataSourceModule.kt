package m_rnd.keingeldfuerdiemensa.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m_rnd.keingeldfuerdiemensa.datasource.MealDataSource
import m_rnd.keingeldfuerdiemensa.datasource.MealDataSourceImpl
import m_rnd.keingeldfuerdiemensa.datasource.api.MensaService
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {
    @Provides
    @Singleton
    fun provideMealDatasource(mensaService: MensaService): MealDataSource = MealDataSourceImpl(mensaService)

}