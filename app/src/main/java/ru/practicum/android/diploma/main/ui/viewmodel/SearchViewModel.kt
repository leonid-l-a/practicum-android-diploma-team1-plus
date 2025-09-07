package ru.practicum.android.diploma.main.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.practicum.android.diploma.main.ui.model.Vacancy
import ru.practicum.android.diploma.main.ui.state.SearchState

class SearchViewModel: ViewModel() {
    private val items = listOf(
        Vacancy(
            id = "af7dd6b8-2367-4695-82df-3470717cee2a",
            name = "Android Developer в Microsoft",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Microsoft_logo.svg/1200px-Microsoft_logo.svg.png",
            industry = "Microsoft",
            salary = "1 000 р"
        )
    )

    private val _searchState = MutableStateFlow<SearchState>(SearchState.Result)
    val searchState: StateFlow<SearchState> = _searchState.asStateFlow()
    val mockData = items
    val totalResult = items.size
}
