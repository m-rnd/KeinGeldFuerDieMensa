package m_rnd.keingeldfuerdiemensa.datasource.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import m_rnd.keingeldfuerdiemensa.entities.ThemeMode
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.entities.util.ErrorReason
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesDataSourceImpl
@Inject constructor(
    private val userPreferencesDataStore: DataStore<UserPreferences>
) : UserPreferencesDataSource {

    override val userPreferences: Flow<FlowState<UserPreferences>> =
        userPreferencesDataStore.data.map {
            FlowState.Success(it)
        }.catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Timber.e("Error reading user preferences.", exception)
                FlowState.Success(UserPreferences.getDefaultInstance())
            } else {
                FlowState.Error(ErrorReason.Unknown)
                throw exception
            }
        }

    override suspend fun setThemeMode(themeMode: ThemeMode): AppResult<Unit> {
        return try {
            userPreferencesDataStore.updateData {
                it.toBuilder().setThemeMode(themeMode.ordinal).build()
            }
            AppResult.Success(Unit)
        } catch (ioException: IOException) {
            Timber.e("Failed to update user preferences", ioException)
            AppResult.Error(ErrorReason.Unknown)
        }
    }

    override suspend fun setDynamicColorsEnabled(enabled: Boolean): AppResult<Unit> {
        return try {
            userPreferencesDataStore.updateData {
                it.toBuilder().setUseDynamicColors(enabled).build()
            }
            AppResult.Success(Unit)
        } catch (ioException: IOException) {
            Timber.e("Failed to update user preferences", ioException)
            AppResult.Error(ErrorReason.Unknown)
        }
    }
}