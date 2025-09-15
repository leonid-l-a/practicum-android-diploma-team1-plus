package ru.practicum.android.diploma.filtration.domain.model

data class Industries(
    val items: List<IndustryDetail>,
    val resultCode: StorageResultCode,
    val errorText: String = "",
)
