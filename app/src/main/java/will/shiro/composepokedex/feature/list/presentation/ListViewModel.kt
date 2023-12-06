package will.shiro.composepokedex.feature.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<ListUiState> = MutableStateFlow(ListUiState.Loading)

    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ListUiState.Loading
    )

    internal fun addPokemon() {
        viewModelScope.launch {
            _uiState.emit(ListUiState.Loading)
            delay(1000)
            _uiState.emit(ListUiState.Success(listOf("Charizard")))
        }
    }
}