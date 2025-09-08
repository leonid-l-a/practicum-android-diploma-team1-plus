package ru.practicum.android.diploma.favorites.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.practicum.android.diploma.favorites.ui.state.FavoriteState

class FavoritesViewModel : ViewModel() {

    companion object {
        private const val LOADING_DEBOUNCE_DELAY = 2000L
    }

    private val _stateFavoritesVacancy = MutableStateFlow<FavoriteState>(FavoriteState.Loading)
    val stateFavoritesVacancy = _stateFavoritesVacancy.asStateFlow()
}
