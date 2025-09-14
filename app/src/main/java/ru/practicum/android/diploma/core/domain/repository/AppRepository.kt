package ru.practicum.android.diploma.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun <T : Any> saveData(key: StorageKey, data: T)

    suspend fun getData(key: StorageKey): String?

    suspend fun getAllData(): Flow<Map<String, String?>>
}
