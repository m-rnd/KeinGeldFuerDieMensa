package m_rnd.keingeldfuerdiemensa.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m_rnd.keingeldfuerdiemensa.datasource.api.ApiMealDataSource
import m_rnd.keingeldfuerdiemensa.datasource.db.DbMensaDataSource
import m_rnd.keingeldfuerdiemensa.repository.MensaRepository
import m_rnd.keingeldfuerdiemensa.repository.MensaRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMensaRepository(
        apiMealDataSource: ApiMealDataSource,
        dbMensaDataSource: DbMensaDataSource,
    ): MensaRepository = MensaRepositoryImpl(
        apiMealDataSource = apiMealDataSource,
        dbMensaDataSource = dbMensaDataSource)
}