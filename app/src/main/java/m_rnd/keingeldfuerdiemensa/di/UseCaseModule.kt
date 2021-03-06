package m_rnd.keingeldfuerdiemensa.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import m_rnd.keingeldfuerdiemensa.repository.CanteenRepository
import m_rnd.keingeldfuerdiemensa.usecase.*

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

    fun provideSetCanteenPriorityUseCase(canteenRepository: CanteenRepository) =
        SetCanteenPriorityUseCase(canteenRepository)

    fun provideSetCanteenVisibleUseCase(canteenRepository: CanteenRepository) =
        SetCanteenVisibleUseCase(canteenRepository)

    fun provideDeleteCanteenUseCase(canteenRepository: CanteenRepository) =
        DeleteCanteenUseCase(canteenRepository)
}