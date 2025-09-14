package ru.practicum.android.diploma.filtration.ui.state

import ru.practicum.android.diploma.filtration.ui.model.data.Industry

sealed class IndustryState {
    object Idle : IndustryState()
    object Loading : IndustryState()
    object EmptyResult : IndustryState()
    data class Content(val items: List<Industry>) : IndustryState()
}
