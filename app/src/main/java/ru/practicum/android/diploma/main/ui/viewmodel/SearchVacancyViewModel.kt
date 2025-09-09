package ru.practicum.android.diploma.main.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.practicum.android.diploma.main.data.model.VacancyDetailMainData
import ru.practicum.android.diploma.main.data.model.VacancyMainData
import ru.practicum.android.diploma.main.domain.interactor.SearchVacancyInteractor
import ru.practicum.android.diploma.main.domain.state.Resource
import ru.practicum.android.diploma.main.ui.model.Vacancy
import ru.practicum.android.diploma.main.ui.state.SearchState
import ru.practicum.android.diploma.main.util.getFormatSalary
import ru.practicum.android.diploma.util.DebounceUtil

class SearchVacancyViewModel(

    val searchVacancyInteractor: SearchVacancyInteractor

) : ViewModel() {

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }

    private var isClickAllowed = true

    private var latestRequestText: String? = null

    private val _stateSearchVacancy = MutableStateFlow<SearchState>(value = SearchState.Default)
    val stateSearchVacancy = _stateSearchVacancy.asStateFlow()

    val debounce = DebounceUtil(
        delayMillis = SEARCH_DEBOUNCE_DELAY,
        coroutineScope = viewModelScope
    )

    private fun searchVacancy(expression: String) {
        if (expression.isNotEmpty()) {
            renderSearchState(
                state = SearchState.Loading
            )
            debounce.invoke {
                searchVacancyInteractor.searchVacancy(expression)
                    .collect { vacancy ->
                        searchState(vacancy)
                    }
            }
        } else {
            debounce.cancel()
        }
    }

    private fun map(items: List<VacancyDetailMainData>): List<Vacancy> {
        return items.map {
            Vacancy(
                name = it.name,
                id = it.id,
                logoUrl = it.employer.logo ?: "",
                industry = it.employer.name ?: "",
                salary = it.salary.getFormatSalary()
            )
        }
    }

    fun searchRequestText(expression: String) {
        if (latestRequestText == expression) {
            return
        }
        latestRequestText = expression
        searchVacancy(expression)
    }

    fun clickDebounce(): Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            debounce.invoke {
                isClickAllowed = true
            }
        }
        return current
    }

    fun renderDefaultState() {
        debounce.cancel()
        latestRequestText = ""
        renderSearchState(SearchState.Default)
    }

    fun searchState(vacancy: Resource<VacancyMainData>) {
        when (vacancy) {
            is Resource.Success -> {
                val vacancy = vacancy.data.items

                if (vacancy.isNotEmpty()) {
                    renderSearchState(
                        state = SearchState.Content(
                            vacancy = map(vacancy)
                        )
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

    fun renderSearchState(state: SearchState) {
        _stateSearchVacancy.value = state
    }
}
