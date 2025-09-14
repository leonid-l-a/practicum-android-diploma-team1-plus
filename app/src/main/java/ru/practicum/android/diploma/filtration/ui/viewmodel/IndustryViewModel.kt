package ru.practicum.android.diploma.filtration.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.practicum.android.diploma.filtration.ui.model.data.Industry
import ru.practicum.android.diploma.filtration.ui.state.IndustryState

class IndustryViewModel : ViewModel() {
    private val demoItems = listOf(
        Industry(
            id = "111",
            name = "Авиаперевозки",
        ),
        Industry(
            id = "222",
            name = "Авиационная, вертолетная и аэрокосмическая промышленность"
        ),
        Industry(
            id = "333",
            name = "Автокомпоненты, запчасти (производство)",
        ),
        Industry(
            id = "444",
            name = "Автокомпоненты, запчасти, шины (продвеждение, оптовая торговля)"
        ),
        Industry(
            id = "555",
            name = "Автомобильные перевозки"
        ),
        Industry(
            id = "777",
            name = "Автошкола"
        ),
        Industry(
            id = "1060",
            name = "Просто школа"
        ),
    )

    private val _industryState = MutableStateFlow<IndustryState>(IndustryState.Idle)
    val industryState = _industryState.asStateFlow()

    init {
        _industryState.value = IndustryState.Content(items = demoItems)
    }

    fun filerItems(input: String) {
        if (input.isEmpty()) {
            _industryState.value = IndustryState.Content(
                items = demoItems
            )
        } else {
            val newList = demoItems.filter {
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
}
