package m_rnd.keingeldfuerdiemensa.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.entities.util.DialogResult
import m_rnd.keingeldfuerdiemensa.entities.util.UiState
import m_rnd.keingeldfuerdiemensa.ui.navigation.Navigator
import m_rnd.keingeldfuerdiemensa.usecase.GetCanteenSearchResultsUseCase
import m_rnd.keingeldfuerdiemensa.usecase.SaveCanteenFromSearchResultUseCase
import javax.inject.Inject



@HiltViewModel
class AddCanteenViewModel @Inject constructor(
    private val navigator: Navigator,
    private val saveCanteenFromSearchResultUseCase: SaveCanteenFromSearchResultUseCase,
    private val getCanteenSearchResultsUseCase: GetCanteenSearchResultsUseCase
) : ViewModel() {

    var canteenSearchInput = mutableStateOf(TextFieldValue(""))
        private set

    var uiState by mutableStateOf<UiState>(UiState.Loading)
        private set

    var nameDialogShowing by mutableStateOf(false)
        private set

    private var availableCanteens = listOf<CanteenSearchResult>()

    var selectedCanteen: CanteenSearchResult? = null
        private set

    var filteredCanteens = mutableStateOf(listOf<CanteenSearchResult>())
        private set


    init {
        viewModelScope.launch {
            loadCanteensForSearchResult()
        }
    }

    private suspend fun loadCanteensForSearchResult() {
        when (val canteenResult = getCanteenSearchResultsUseCase(Unit)) {
            is AppResult.Error -> uiState = UiState.Error(canteenResult.reason)
            is AppResult.Success -> {
                uiState = UiState.Ready
                availableCanteens = canteenResult.data
            }
        }
    }

    fun onCanteenInputChanged(newValue: TextFieldValue) {
        canteenSearchInput.value = newValue
        filteredCanteens.value = when (newValue.text.length) {
            in 0..1 -> listOf()
            else -> availableCanteens.filter { canteen ->
                val subStrings = newValue.text.split(" ")
                var subStringsFound = 0
                subStrings.forEach {
                    if (canteen.name.contains(it, true)
                        || canteen.city.contains(it, true)
                        || canteen.address.contains(it, true)
                    ) {
                        subStringsFound++
                    }
                }
                subStringsFound == subStrings.size
            }
        }
    }

    fun onCanteenClicked(canteen: CanteenSearchResult) {
        selectedCanteen = canteen
        nameDialogShowing = true
    }

    fun onNameDialogResult(result: DialogResult) = viewModelScope.launch {
        nameDialogShowing = false
        if (result is DialogResult.Positive.CanteenName) {
            selectedCanteen?.let { canteen ->

                val newCanteen = Canteen(
                    id = canteen.id,
                    name = result.value
                )
                saveCanteenFromSearchResultUseCase(newCanteen)
                navigator.navigateUp()
            }
        }
    }

    fun navigateUp() {
        navigator.navigateUp()
    }
}