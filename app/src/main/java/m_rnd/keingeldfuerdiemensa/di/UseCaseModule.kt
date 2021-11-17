package m_rnd.keingeldfuerdiemensa.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import m_rnd.keingeldfuerdiemensa.repository.CanteenRepository
import m_rnd.keingeldfuerdiemensa.usecase.GetCanteenSearchResultsUseCase
import m_rnd.keingeldfuerdiemensa.usecase.GetCanteensUseCase
import m_rnd.keingeldfuerdiemensa.usecase.GetCanteensWithMealsUseCase
import m_rnd.keingeldfuerdiemensa.usecase.SaveCanteenFromSearchResultUseCase

@InstallIn(ViewModelComponent::class)
@Module
object UseCaseModule {

    fun provideGetCanteensWithMealsUseCase(canteenRepository: CanteenRepository) =
        GetCanteensWithMealsUseCase(canteenRepository)

    fun provideGetCanteensUseCase(canteenRepository: CanteenRepository) =
        GetCanteensUseCase(canteenRepository)

    fun provideGetCanteenSearchResultsUseCase(canteenRepository: CanteenRepository) =
        GetCanteenSearchResultsUseCase(canteenRepository)

    fun provideSaveCanteenFromSearchResultUseCase(canteenRepository: CanteenRepository) =
        SaveCanteenFromSearchResultUseCase(canteenRepository)
}