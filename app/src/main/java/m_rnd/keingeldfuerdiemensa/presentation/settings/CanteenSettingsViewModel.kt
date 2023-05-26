package m_rnd.keingeldfuerdiemensa.presentation.settings

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.util.ErrorReason
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState
import m_rnd.keingeldfuerdiemensa.entities.util.NavigationTarget
import m_rnd.keingeldfuerdiemensa.ui.navigation.Navigator
import m_rnd.keingeldfuerdiemensa.usecase.DeleteCanteenUseCase
import m_rnd.keingeldfuerdiemensa.usecase.GetCanteensUseCase
import m_rnd.keingeldfuerdiemensa.usecase.SetCanteenPriorityUseCase
import m_rnd.keingeldfuerdiemensa.usecase.SetCanteenVisibleUseCase
import javax.inject.Inject

sealed class CanteenList {
    class Reorderable(
        val canteens: SnapshotStateList<Canteen>
    ) : CanteenList()

    object EmptyList : CanteenList()

    class Dismissible(
        val canteens: List<Canteen>
    ) : CanteenList()
}

@HiltViewModel
class CanteenSettingsViewModel @Inject constructor(
    getCanteensUseCase: GetCanteensUseCase,
    private val navigator: Navigator,
    private val setCanteenVisibleUseCase: SetCanteenVisibleUseCase,
    private val setCanteenPriorityUseCase: SetCanteenPriorityUseCase,
    private val deleteCanteenUseCase: DeleteCanteenUseCase
) : ViewModel() {

    private val sortedCanteens = MutableStateFlow(listOf<Canteen>().toMutableStateList())

    private val dbCanteens: StateFlow<List<Canteen>> = getCanteensUseCase(Unit).map {
        if (it is FlowState.Error) {
            if (it.reason == ErrorReason.Db.EmptyResult) FlowState.Success(emptyList()) else it
        } else it
    }.mapNotNull {
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

    var canteensFlow =
        combine(dbCanteens, _isSortEnabled, sortedCanteens) { canteens, isSortEnabled, sorted ->
            if (isSortEnabled) {
                if (sorted.isNotEmpty()) CanteenList.Reorderable(sorted) else CanteenList.EmptyList
            } else {
                if (canteens.isNotEmpty()) CanteenList.Dismissible(canteens) else CanteenList.EmptyList
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CanteenList.EmptyList
        )


    fun deleteCanteen(canteen: Canteen) = viewModelScope.launch {
        delay(500) // delay to finish the swipe animation before deleting it
        deleteCanteenUseCase(canteen)
    }

    fun toggleSortMode() = viewModelScope.launch {
        if (_isSortEnabled.value) {
            //save order
            sortedCanteens.value.forEachIndexed { index, canteen ->
                setCanteenPriorityUseCase(SetCanteenPriorityUseCase.Input(canteen, index))
            }
        } else {
            //enable sort by emitting the current list as a state list
            sortedCanteens.emit(dbCanteens.value.toMutableStateList())
        }
        _isSortEnabled.emit(_isSortEnabled.value.not())

    }

    fun toggleCanteenVisibility(canteen: Canteen) = viewModelScope.launch {
        setCanteenVisibleUseCase(SetCanteenVisibleUseCase.Input(canteen, canteen.isVisible.not()))
    }


    fun moveCanteen(from: Int, to: Int) = viewModelScope.launch {
        val current = sortedCanteens.value
        current.add(to, current.removeAt(from))
        sortedCanteens.emit(current)
    }

    fun navigateToAddCanteenScreen() {
        navigator.navigateTo(NavigationTarget.AddCanteen)
    }

    fun navigateUp() {
        navigator.navigateUp()
    }
}