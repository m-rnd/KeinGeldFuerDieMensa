package m_rnd.keingeldfuerdiemensa.entities.util

sealed class UiState {
    object Loading: UiState()
    object Ready: UiState()
    class Error(val reason: ErrorReason): UiState()
}