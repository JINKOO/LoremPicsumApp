package com.kjk.lorempicsumapp.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel : ViewModel() {

    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Idle)
    val homeUiState = _homeUiState.asStateFlow()

    fun onEvent(homeEvent: HomeEvent) {
        when(homeEvent) {
            is HomeEvent.FetchPictures -> {
                fetchPicture()
            }
        }
    }

    private fun fetchPicture() {
        viewModelScope.launch(Dispatchers.IO) {
            // TODO fetch pictures
        }
    }
}