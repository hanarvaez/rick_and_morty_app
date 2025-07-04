package co.com.monkeymobile.rick_and_morty_app.presentation.state

sealed class UiState {

    object Loading : UiState()
    object Success : UiState()
    data class Error(val message: String) : UiState()
}
