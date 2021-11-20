package m_rnd.keingeldfuerdiemensa.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import m_rnd.keingeldfuerdiemensa.entities.util.ErrorReason
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState
import timber.log.Timber

abstract class FlowUseCase<In, Out> {


    abstract fun call(input: In): Flow<FlowState<Out>>

    operator fun invoke(input: In): Flow<FlowState<Out>> {
        return call(input).catch { throwable ->
            FlowState.Error(ErrorReason.UseCaseException(throwable))
        }.map {
            Timber.i("flow update of ${this.javaClass.simpleName}: $it")
            it
        }
    }
}
