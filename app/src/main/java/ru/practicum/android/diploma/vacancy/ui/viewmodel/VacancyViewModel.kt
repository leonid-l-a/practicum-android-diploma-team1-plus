package ru.practicum.android.diploma.vacancy.ui.viewmodel

import android.content.Intent
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.vacancy.domain.interactor.ShareVacancyDetailInteractor
import ru.practicum.android.diploma.vacancy.domain.interactor.VacancyDetailUseCase
import ru.practicum.android.diploma.vacancy.ui.state.VacancyState
import java.io.IOException

class VacancyViewModel(
    val vacancyDetailUseCase: VacancyDetailUseCase,
    savedStateHandle: SavedStateHandle,
    val shareVacancyDetailInteractor: ShareVacancyDetailInteractor,
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
        return when (val currentState = _state.value) {
            is VacancyState.Success ->
                shareVacancyDetailInteractor.shareVacancyWithMessenger(
                    currentState.vacancyDetail.url
                )
            else -> null
        }
    }

    fun writeWithMail(): Intent? {
        return when (val currentState = _state.value) {
            is VacancyState.Success -> {
                val contact = currentState.vacancyDetail.contacts.email
                shareVacancyDetailInteractor.writeWithMail(contact)
            }
            else -> null
        }
    }

    fun callWithPhone(): Intent? {
        return when (val currentState = _state.value) {
            is VacancyState.Success -> {
                val contacts = currentState.vacancyDetail.contacts
                val phone = contacts.phone?.firstOrNull()
                if (!phone.isNullOrEmpty()) {
                    shareVacancyDetailInteractor.callWithPhone(phone)
                } else {
                    null
                }
            }
            else -> null
        }
    }

}
