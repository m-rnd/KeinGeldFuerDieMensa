package m_rnd.keingeldfuerdiemensa.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m_rnd.keingeldfuerdiemensa.datasource.MealDataSource
import m_rnd.keingeldfuerdiemensa.repository.MealRepository
import m_rnd.keingeldfuerdiemensa.repository.MealRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMealRepository(mealDataSource: MealDataSource): MealRepository = MealRepositoryImpl(mealDataSource)
}