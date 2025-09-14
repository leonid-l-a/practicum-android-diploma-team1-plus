package ru.practicum.android.diploma.main.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.domain.AppInteractor
import ru.practicum.android.diploma.main.data.model.VacancyDetailMainData
import ru.practicum.android.diploma.main.data.model.VacancyMainData
import ru.practicum.android.diploma.main.domain.interactor.SearchVacancyInteractor
import ru.practicum.android.diploma.main.domain.state.Resource
import ru.practicum.android.diploma.main.ui.model.Vacancy
import ru.practicum.android.diploma.main.ui.state.SearchState
import ru.practicum.android.diploma.main.util.getFormatSalary
import ru.practicum.android.diploma.util.DebounceUtil

class SearchVacancyViewModel(

    val searchVacancyInteractor: SearchVacancyInteractor,
    val appInteractor: AppInteractor

) : ViewModel() {

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }

    private var currentPage = 1
    private var maxPages = 0
    private var isClickAllowed = true
    private var latestRequestText: String? = null
    private val _stateSearchVacancy = MutableStateFlow<SearchState>(value = SearchState.Default)
    val stateSearchVacancy = _stateSearchVacancy.asStateFlow()

    private val _stateSearchFilter = MutableStateFlow<Map<String, String?>>(value = emptyMap())
    val stateSearchFilter = _stateSearchVacancy.asStateFlow()

    init {
        viewModelScope.launch {
            appInteractor.getAllData().collect { data ->
                _stateSearchFilter.value = data
            }
        }
    }

    val debounce = DebounceUtil(
        delayMillis = SEARCH_DEBOUNCE_DELAY,
        coroutineScope = viewModelScope
    )

    private fun resetPages() {
        currentPage = 1
        maxPages = 0
    }

    private fun searchVacancy(expression: String, isLazyLoad: Boolean = false) {
        if (expression.isNotEmpty()) {
            if (!isLazyLoad) {
                renderSearchState(
                    state = SearchState.Loading
                )
            }
            val currentContent = _stateSearchVacancy.value as? SearchState.Content
            currentContent?.let {
                renderSearchState(
                    state = it.copy(
                        isLoadingNextPage = true
                    )
                )
            }

            debounce.invoke {
                val filter: Map<String, String?> = _stateSearchFilter.value
                searchVacancyInteractor.searchVacancy(expression, currentPage, filter)
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
                salary = it.salary.getFormatSalary(),
                city = it.city
            )
        }
    }

    fun showMore() {
        if (currentPage <= maxPages) {
            latestRequestText?.let {
                if (it.isNotEmpty()) {
                    searchRequestText(expression = it, isLazyLoad = true)
                }
            }
        }
    }

    fun searchRequestText(expression: String, isLazyLoad: Boolean = false) {
        if (latestRequestText == expression) {
            if (!isLazyLoad) {
                return
            }
        } else {
            if (latestRequestText?.isNotEmpty() == true && !isLazyLoad) {
                resetPages()
            }
        }
        if (expression.isEmpty()) {
            resetPages()
        }
        latestRequestText = expression
        searchVacancy(
            expression = expression,
            isLazyLoad = isLazyLoad
        )
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
        resetPages()
        latestRequestText = ""
        renderSearchState(SearchState.Default)
    }

    fun searchState(vacancy: Resource<VacancyMainData>) {
        when (vacancy) {
            is Resource.Success -> {
                val vacancy = vacancy.data
                if (maxPages == 0) {
                    maxPages = vacancy.pages
                }
                if (vacancy.items.isNotEmpty()) {
                    var vacancies = map(vacancy.items)
                    val state = stateSearchVacancy.value
                    if (state is SearchState.Content) {
                        vacancies = state.vacancy + vacancies
                    }
                    renderSearchState(
                        state = SearchState.Content(
                            vacancy = vacancies,
                            countVacancy = vacancy.found,
                            isLoadingNextPage = false
                        )
                    )
                    currentPage++
                } else {
                    renderSearchState(
                        state = SearchState.Empty
                    )
                }
            }

            is Resource.Error -> {
                renderSearchState(
                    state = SearchState.Error
                )
            }
        }
    }

    fun renderSearchState(state: SearchState) {
        _stateSearchVacancy.value = state
    }
}
