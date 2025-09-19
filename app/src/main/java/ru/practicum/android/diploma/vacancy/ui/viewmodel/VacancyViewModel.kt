package ru.practicum.android.diploma.vacancy.ui.viewmodel

import android.content.Intent
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.vacancy.domain.interactor.VacancyDetailUseCase
import ru.practicum.android.diploma.vacancy.ui.state.VacancyState
import ru.practicum.android.diploma.favorites.domain.interactor.FavoritesInteractor
import ru.practicum.android.diploma.util.NetworkUtil
import java.io.IOException

class VacancyViewModel(
    private val vacancyDetailUseCase: VacancyDetailUseCase,
    savedStateHandle: SavedStateHandle,
    private val favoritesInteractor: FavoritesInteractor,
    private val networkUtil: NetworkUtil,
) : ViewModel() {
    private val _state = MutableStateFlow<VacancyState>(VacancyState.Loading)
    val state: StateFlow<VacancyState> = _state

    private val vacancyId: String = checkNotNull(savedStateHandle["vacancyId"])

    init {
        loadVacancy()
    }

    private fun loadVacancy() {
        if (!networkUtil.isInternetAvailable()) {
            _state.value = VacancyState.Error
            return
        }

        viewModelScope.launch {
            try {
                val vacancyDetail = vacancyDetailUseCase.getVacancyDetail(vacancyId)
                _state.value = VacancyState.Success(vacancyDetail, false)
                checkInFavorites()
            } catch (e: IOException) {
                _state.value = VacancyState.ServerError
                Log.e("VacancyViewModel", "Server error", e)
            }
        }
    }

    fun checkInFavorites() {
        val currentState = _state.value
        if (currentState is VacancyState.Success) {
            viewModelScope.launch {
                val vacancy = currentState.vacancyDetail
                val existing = favoritesInteractor.findFavoriteVacancy(vacancy)
                _state.value = currentState.copy(isFavorite = existing != null)
            }
        }
    }

    fun toggleFavorite() {
        val currentState = _state.value
        if (currentState is VacancyState.Success) {
            viewModelScope.launch {
                val vacancy = currentState.vacancyDetail
                val existing = favoritesInteractor.findFavoriteVacancy(vacancy)
                if (existing != null) {
                    favoritesInteractor.deleteFromFavorites(vacancy)
                    _state.value = currentState.copy(isFavorite = false)
                } else {
                    favoritesInteractor.addFavoriteVacancy(vacancy)
                    _state.value = currentState.copy(isFavorite = true)
                }
            }
        }
    }

    fun shareVacancyWithMessenger(): Intent? {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, vacancyId)
            type = "text/plain"
        }
        return Intent.createChooser(intent, null)
    }

    fun writeWithMail(): Intent? {
        val currentState = _state.value
        if (currentState is VacancyState.Success) {
            val email = currentState.vacancyDetail.contactsEmail
            return Intent.createChooser(
                Intent(Intent.ACTION_SENDTO).apply {
                    data = "mailto:".toUri()
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                },
                null
            )
        }
        return null
    }

    fun callWithPhone(): Intent? {
        val currentState = _state.value
        if (currentState is VacancyState.Success) {
            val phones = currentState.vacancyDetail.contactsPhone
            if (!phones.isNullOrEmpty()) {
                return Intent.createChooser(
                    Intent(Intent.ACTION_DIAL).apply {
                        data = "tel:${phones.first()}".toUri()
                    },
                    null
                )
            }
        }
        return null
    }
}
