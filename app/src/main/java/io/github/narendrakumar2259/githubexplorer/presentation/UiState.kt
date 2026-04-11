package io.github.narendrakumar2259.githubexplorer.presentation

import io.github.narendrakumar2259.githubexplorer.domain.model.UserDetails

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Success(val userDetails: UserDetails) : UiState()
    data class Error(val message: String) : UiState()
}
