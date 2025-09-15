package ru.practicum.android.diploma.filtration.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.domain.AppInteractor
import ru.practicum.android.diploma.core.domain.repository.StorageKey
import ru.practicum.android.diploma.filtration.domain.interactor.IndustriesInteractor
import ru.practicum.android.diploma.filtration.domain.model.Industries
import ru.practicum.android.diploma.filtration.domain.model.IndustryDetail
import ru.practicum.android.diploma.filtration.domain.model.StorageResultCode
import ru.practicum.android.diploma.filtration.domain.state.Resource
import ru.practicum.android.diploma.filtration.ui.model.data.Industry
import ru.practicum.android.diploma.filtration.ui.state.IndustryState

class IndustryViewModel(
    val industriesInteractor: IndustriesInteractor,
    val appInteractor: AppInteractor
) : ViewModel() {
    private var items = emptyList<Industry>()

    private val _industryState = MutableStateFlow<IndustryState>(IndustryState.Idle)
    val industryState = _industryState.asStateFlow()

    init {
        _industryState.value = IndustryState.Loading
        viewModelScope.launch {
            val industries = industriesInteractor.getIndustries()
            industries.collect {
                industryState(state = it)
            }
            //items = map(industries = industries)
            //_industryState.value = IndustryState.Content(items = items)

        }
    }

    private fun map(industries: List<IndustryDetail>): List<Industry> {
        return industries.map {
            Industry(
                id = it.id,
                name = it.name
            )
        }
    }

    fun filerItems(input: String) {
        if (input.isEmpty()) {
            _industryState.value = IndustryState.Content(
                items = items
            )
        } else {
            val newList = items.filter {
                it.name.contains(input, ignoreCase = true)
            }
            if (newList.isNotEmpty()) {
                _industryState.value = IndustryState.Content(
                    items = newList
                )
            } else {
                _industryState.value = IndustryState.EmptyResult
            }
        }
    }

    private fun industryState(state: Resource<Industries>) {
        when (state) {
            is Resource.Error -> {
                when (state.data.resultCode) {
                    StorageResultCode.SUCCESS -> {}
                    StorageResultCode.CLIENT_ERROR -> {
                        renderState(
                            IndustryState.ClientError
                        )
                    }
                    StorageResultCode.SERVER_ERROR -> {
                        renderState(
                            IndustryState.ServerError
                        )
                    }
                }
            }
            is Resource.Success -> {
                items = map(industries = state.data.items)
                renderState(
                    state = IndustryState.Content(items = items)
                )
            }
        }
    }

    private fun renderState(state: IndustryState) {
        _industryState.value = state
    }
}
