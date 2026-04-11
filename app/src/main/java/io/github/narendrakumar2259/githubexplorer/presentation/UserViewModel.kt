package io.github.narendrakumar2259.githubexplorer.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.narendrakumar2259.githubexplorer.domain.usecase.GetUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
): ViewModel() {
    private val _userDetailsState = MutableStateFlow<UiState>(UiState.Idle)
    val userDetailsState: StateFlow<UiState> = _userDetailsState


    private var _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    init {
        viewModelScope.launch {
            _searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .collect { query ->
                    if (query.isNotEmpty()) {
                        fetchUserDetails(query)
                    } else {
                        _userDetailsState.value = UiState.Idle
                    }
                }
        }
    }

    fun fetchUserDetails(username: String) {
        _userDetailsState.value = UiState.Loading
        viewModelScope.launch {
            getUserUseCase(username)
                .onSuccess { user ->
                    _userDetailsState.value = UiState.Success(user)
                }
                .onFailure { error ->
                    _userDetailsState.value = UiState.Error(error.message ?: "Unknown error")
                }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}