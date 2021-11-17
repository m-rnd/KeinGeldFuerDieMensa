package m_rnd.keingeldfuerdiemensa.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.entities.util.ErrorReason
import timber.log.Timber
import java.lang.Exception

abstract class SuspendUseCase<In, Out> {

    internal abstract val call: suspend (In) -> AppResult<Out>

    suspend operator fun invoke(input: In): AppResult<Out> {
        return try {
            call(input).also {
                Timber.i("result of ${this.javaClass.simpleName}: $it")
            }
        }catch (e: Exception){
            AppResult.Error(ErrorReason.UseCaseException(e))
        }
    }
}
