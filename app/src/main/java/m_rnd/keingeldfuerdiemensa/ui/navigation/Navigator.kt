package m_rnd.keingeldfuerdiemensa.ui.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import m_rnd.keingeldfuerdiemensa.entities.util.NavigationTarget
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {
    private val _sharedFlow = MutableSharedFlow<NavigationTarget>(extraBufferCapacity = 1)
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun navigateTo(navTarget: NavigationTarget) {
        _sharedFlow.tryEmit(navTarget)
    }

    fun navigateUp() {
        _sharedFlow.tryEmit(NavigationTarget.Up)
    }
}