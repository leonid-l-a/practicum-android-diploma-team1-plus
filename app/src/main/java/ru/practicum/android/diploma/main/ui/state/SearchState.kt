package ru.practicum.android.diploma.main.ui.state

sealed interface SearchState {
    object Idle: SearchState
    object Loading: SearchState
    object Result: SearchState
    object EmptyResult: SearchState
    object Error: SearchState
}
