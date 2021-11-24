package m_rnd.keingeldfuerdiemensa.presentation.settings

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState
import m_rnd.keingeldfuerdiemensa.usecase.GetCanteensUseCase
import org.burnoutcrew.reorderable.move
import javax.inject.Inject

sealed class CanteenList {
    class Reorderable(
        val canteens: SnapshotStateList<Canteen>
    ) : CanteenList()

    object EmptyList: CanteenList()

    class Dismissible(
        val canteens: List<Canteen>
    ) : CanteenList()
}

@HiltViewModel
class CanteenSettingsViewModel @Inject constructor(
    getCanteensUseCase: GetCanteensUseCase
) : ViewModel() {

    private val sortedCanteens = MutableStateFlow(listOf<Canteen>().toMutableStateList())

    private val dbCanteens: StateFlow<List<Canteen>> = getCanteensUseCase(Unit)
        .mapNotNull {
        if (it is FlowState.Success) {
            it.data
        } else null
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(1000L),
        initialValue = listOf()
    )

    private val _isSortEnabled = MutableStateFlow(false)
    val isSortEnabled: StateFlow<Boolean> = _isSortEnabled

    var canteensFlow = combine(dbCanteens, _isSortEnabled, sortedCanteens) { canteens, isSortEnabled, sorted ->
        if (isSortEnabled) {
            CanteenList.Reorderable(sorted)
        } else {
            CanteenList.Dismissible(canteens)
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        CanteenList.EmptyList
    )


    fun deleteCanteen(canteen: Canteen) {

    }

    fun toggleSortMode() = viewModelScope.launch {
        if (_isSortEnabled.value) {
            //disable sort and save order
        } else {
            //enable sort
            sortedCanteens.emit(dbCanteens.value.toMutableStateList())
        }
        _isSortEnabled.emit(_isSortEnabled.value.not())

    }

    fun toggleCanteenVisibility(canteen: Canteen) {

    }


    fun moveCanteen(from: Int, to: Int) = viewModelScope.launch {
        val current = sortedCanteens.value
        current.move(from,to)
        sortedCanteens.emit(current)
    }
}