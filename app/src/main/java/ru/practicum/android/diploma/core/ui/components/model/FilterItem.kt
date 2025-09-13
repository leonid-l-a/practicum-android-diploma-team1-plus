package ru.practicum.android.diploma.core.ui.components.model

import ru.practicum.android.diploma.core.ui.components.FilterParams

data class FilterItem(
    val idValue: String,
    val textLabel: String,
    val checked: Boolean,
    val textLabelValue: String = "",
    val isMainField: Boolean = false,
    val typeField: FilterParams.FIELDTYPE = FilterParams.FIELDTYPE.TEXT
)
