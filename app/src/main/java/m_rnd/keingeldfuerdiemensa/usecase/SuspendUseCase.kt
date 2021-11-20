package m_rnd.keingeldfuerdiemensa.usecase

import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.entities.util.ErrorReason
import timber.log.Timber

abstract class SuspendUseCase<In, Out> {

    abstract suspend fun call(input: In): AppResult<Out>

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
