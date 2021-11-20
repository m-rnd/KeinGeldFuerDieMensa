package m_rnd.keingeldfuerdiemensa.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.usecase.GetCanteenSearchResultsUseCase
import m_rnd.keingeldfuerdiemensa.usecase.SaveCanteenFromSearchResultUseCase
import javax.inject.Inject

@HiltViewModel
class AddCanteenViewModel @Inject constructor(
    private val getCanteenSearchResultsUseCase: GetCanteenSearchResultsUseCase,
    private val saveCanteenFromSearchResultUseCase: SaveCanteenFromSearchResultUseCase
) : ViewModel() {

    var canteenSearchInput = mutableStateOf(TextFieldValue(""))
        private set

    var isLoading = mutableStateOf(false)
        private set


    private var availableCanteens = listOf<CanteenSearchResult>()

    var filteredCanteens = mutableStateOf(listOf<CanteenSearchResult>())
        private set


    init {
        viewModelScope.launch {
            loadCanteensForSearchResult()
        }
    }

    private suspend fun loadCanteensForSearchResult() {
        isLoading.value = true
        when (val canteenResult = getCanteenSearchResultsUseCase(Unit)) {
            is AppResult.Error -> isLoading.value = false
            is AppResult.Success -> {
                isLoading.value = false
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
                            || canteen.address.contains(it, true)) {
                        subStringsFound++
                    }
                }
                subStringsFound == subStrings.size
            }
        }
    }

    fun onCanteenClicked(canteen: CanteenSearchResult) {
        viewModelScope.launch {
            saveCanteenFromSearchResultUseCase(canteen)
        }
    }
}