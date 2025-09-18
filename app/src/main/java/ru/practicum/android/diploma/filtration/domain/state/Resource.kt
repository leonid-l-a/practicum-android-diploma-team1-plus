package ru.practicum.android.diploma.filtration.domain.state

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val data: T) : Resource<T>()
}
