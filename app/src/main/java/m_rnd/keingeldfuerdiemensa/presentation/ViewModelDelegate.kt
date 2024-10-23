package m_rnd.keingeldfuerdiemensa.presentation

import kotlinx.coroutines.CoroutineScope

abstract class ViewModelDelegate {
    lateinit var viewModelScope: CoroutineScope

    operator fun invoke(scope: CoroutineScope) {
        this.viewModelScope = scope
        afterParentViewModelInit()
    }

    open fun afterParentViewModelInit() {}
}
