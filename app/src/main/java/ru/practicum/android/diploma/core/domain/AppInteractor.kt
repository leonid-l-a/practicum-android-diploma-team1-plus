package ru.practicum.android.diploma.core.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.domain.repository.StorageKey

interface AppInteractor {

    fun <T : Any> saveData(key: StorageKey, data: T)

    suspend fun getData(key: StorageKey): String?

    suspend fun getAllData(): Flow<Map<String, String?>>
}
