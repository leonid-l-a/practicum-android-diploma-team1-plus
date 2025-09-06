package ru.practicum.android.diploma.main.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.practicum.android.diploma.core.data.dto.Vacancy
import ru.practicum.android.diploma.main.domain.interactor.SearchVacancyInteractor
import ru.practicum.android.diploma.main.domain.model.Resource
import ru.practicum.android.diploma.main.ui.state.SearchState
import ru.practicum.android.diploma.util.DebounceUtil

class SearchVacancyViewModel(

    val searchVacancyInteractor: SearchVacancyInteractor

) : ViewModel() {

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }

    private val _stateSearchVacancy = MutableStateFlow<SearchState>(value = SearchState.Default)
    val stateSearchVacancy = _stateSearchVacancy.asStateFlow()

    val debounce = DebounceUtil(
        delayMillis = SEARCH_DEBOUNCE_DELAY,
        coroutineScope = viewModelScope
    )

    fun searchVacancy(expression: String){
        if(expression.isNotEmpty()){
            renderSearchState(
                state = SearchState.Loading
            )
            debounce.invoke {
                searchVacancyInteractor.searchVacancy(expression)
                    .collect { vacancy ->
                        searchState(vacancy)
                }
            }
        }

    }

    fun searchState(vacancy: Resource<Vacancy>){
        when (vacancy){
            is Resource.Success -> {
                val vacancy = vacancy.data.items

                if (vacancy.isNotEmpty()){
                    renderSearchState(
                        state = SearchState.Content(
                            vacancy=vacancy)
                    )
                } else {
                    renderSearchState(
                        state = SearchState.Empty
                    )
                }
            }
            is Resource.Error -> {
                renderSearchState(
                    state = SearchState.Error(
                        message = vacancy.message
                    )
                )
            }
        }
    }

    fun renderSearchState(state: SearchState){
        _stateSearchVacancy.value = state
    }
}
