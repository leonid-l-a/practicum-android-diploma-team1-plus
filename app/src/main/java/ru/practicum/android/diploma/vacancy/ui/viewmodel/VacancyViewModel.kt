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
import java.io.IOException

class VacancyViewModel(
    val vacancyDetailUseCase: VacancyDetailUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = MutableStateFlow<VacancyState>(VacancyState.Loading)

    val state: StateFlow<VacancyState> = _state

    private val vacancyId: String = checkNotNull(savedStateHandle["vacancyId"])

    init {
        viewModelScope.launch {
            try {
                val vacancyDetail = vacancyDetailUseCase.getVacancyDetail(vacancyId)
                _state.value = VacancyState.Success(vacancyDetail, false)
            } catch (e: IOException) {
                _state.value = VacancyState.ServerError
                Log.e("VacancyViewModel", "Server error", e)
            }
        }
    }

    fun shareVacancyWithMessenger(): Intent? {
        return Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                vacancyId
            )
            type = "text/plain"
        }, null)
    }

    fun writeWithMail(): Intent? {
        return when (val currentState = _state.value) {
            is VacancyState.Success -> {
                val contacts = currentState.vacancyDetail.contacts
                val email = contacts.email.firstOrNull()
                return Intent.createChooser(
                    Intent(Intent.ACTION_SENDTO).apply {
                        data = "mailto:".toUri()
                        putExtra(
                            Intent.EXTRA_EMAIL,
                            arrayOf(email))
                    }, null
                )
            }
            else -> null
        }
    }

    fun callWithPhone(): Intent? {
        return when (val currentState = _state.value) {
            is VacancyState.Success -> {
                val contacts = currentState.vacancyDetail.contacts
                val phone = contacts.phone?.firstOrNull()
                if (phone != null) {
                    Intent.createChooser(Intent(Intent.ACTION_DIAL).apply {
                        data = "tel:$phone".toUri()
                    }, null)
                } else {
                    null
                }
            }
            else -> null
        }
    }
}
