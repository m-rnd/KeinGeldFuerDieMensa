package m_rnd.keingeldfuerdiemensa.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.entities.util.ErrorReason
import timber.log.Timber

abstract class UseCase<In, Out> {

    internal abstract val call: (In) -> Flow<AppResult<Out>>

    operator fun invoke(input: In): Flow<AppResult<Out>> {
        return call(input).catch { throwable ->
            AppResult.Error(ErrorReason.UseCaseException(throwable))
        }.map {
            Timber.i("flow update of ${this.javaClass.simpleName}: $it")
            it
        }
    }
}
