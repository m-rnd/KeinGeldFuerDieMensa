package m_rnd.keingeldfuerdiemensa.presentation.settings

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.util.ErrorReason
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState
import m_rnd.keingeldfuerdiemensa.presentation.ViewModelDelegate
import m_rnd.keingeldfuerdiemensa.usecase.DeleteCanteenUseCase
import m_rnd.keingeldfuerdiemensa.usecase.GetCanteensUseCase
import m_rnd.keingeldfuerdiemensa.usecase.SetCanteenPriorityUseCase
import m_rnd.keingeldfuerdiemensa.usecase.SetCanteenVisibleUseCase
import javax.inject.Inject


sealed class CanteenList {
    class Reorderable(
        val canteens: SnapshotStateList<Canteen>
    ) : CanteenList()

    data object EmptyList : CanteenList()

    class Dismissible(
        val canteens: List<Canteen>
    ) : CanteenList()
}

class CanteenSettingsDelegateImpl @Inject constructor(
    getCanteensUseCase: GetCanteensUseCase,
    private val setCanteenVisibleUseCase: SetCanteenVisibleUseCase,
    private val setCanteenPriorityUseCase: SetCanteenPriorityUseCase,
    private val deleteCanteenUseCase: DeleteCanteenUseCase
) : ViewModelDelegate(), CanteenSettingsDelegate {

    private val sortedCanteens = MutableStateFlow(listOf<Canteen>().toMutableStateList())

    private val dbCanteens: StateFlow<List<Canteen>> by lazy {
        getCanteensUseCase(Unit).map {
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
    }

    private val _isSortEnabled = MutableStateFlow(false)
    override val isSortEnabled: StateFlow<Boolean> = _isSortEnabled

    override val canteensFlow by lazy {
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
    }

    override fun deleteCanteen(canteen: Canteen) {
        viewModelScope.launch {
            delay(500) // delay to finish the swipe animation before deleting it
            deleteCanteenUseCase(canteen)
        }
    }

    override fun toggleSortMode() {
        viewModelScope.launch {
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
    }

    override fun toggleCanteenVisibility(canteen: Canteen) {
        viewModelScope.launch {
            setCanteenVisibleUseCase(
                SetCanteenVisibleUseCase.Input(
                    canteen,
                    canteen.isVisible.not()
                )
            )
        }
    }


    override fun moveCanteen(from: Int, to: Int) {
        viewModelScope.launch {
            val current = sortedCanteens.value
            current.add(to, current.removeAt(from))
            sortedCanteens.emit(current)
        }
    }
}