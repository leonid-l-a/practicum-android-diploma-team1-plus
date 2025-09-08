package ru.practicum.android.diploma.favorites.ui.state

import ru.practicum.android.diploma.favorites.ui.model.Vacancy

sealed class FavoriteState {

    data object Loading : FavoriteState()

    data class Content(
        val vacancy: List<Vacancy>
    ) : FavoriteState()

    data object Empty : FavoriteState()

    data class Error(
        val message: String
    ) : FavoriteState()
}
