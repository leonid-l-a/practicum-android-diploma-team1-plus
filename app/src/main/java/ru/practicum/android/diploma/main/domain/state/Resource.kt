package ru.practicum.android.diploma.main.domain.state

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    class Error<T> : Resource<T>()
}
