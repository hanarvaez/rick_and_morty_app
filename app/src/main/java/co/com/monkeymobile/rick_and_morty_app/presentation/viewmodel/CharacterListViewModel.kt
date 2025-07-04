package co.com.monkeymobile.rick_and_morty_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import co.com.monkeymobile.rick_and_morty_app.domain.model.Character
import co.com.monkeymobile.rick_and_morty_app.domain.repository.CharacterRepository
import co.com.monkeymobile.rick_and_morty_app.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    val charactersPagingData: Flow<PagingData<Character>> = repository.getPagedCharacters()
        .onStart { _uiState.value = UiState.Loading }
        .catch { exception ->
            _uiState.value = UiState.Error(exception.message ?: "Unknown error")
            emit(PagingData.empty())
        }
        .onEach { pagingData ->
            if (_uiState.value !is UiState.Error) {
                _uiState.value = UiState.Success
            }
        }
        .cachedIn(viewModelScope)

}
