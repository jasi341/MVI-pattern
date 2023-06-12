package com.example.mvi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class TodoViewModel : ViewModel() {
    val state = mutableStateOf<TodoState>(TodoState.Loading)
    val currentState: TodoState
        get() = state.value

    fun handleIntent(intent: TodoIntent) {
        viewModelScope.launch {

            try {
                val newState = todoReducer(state.value, intent)
                state.value = newState
            } catch (e: Exception) {
                state.value = TodoState.Error("Error fetching data")
            }

        }

    }
}


