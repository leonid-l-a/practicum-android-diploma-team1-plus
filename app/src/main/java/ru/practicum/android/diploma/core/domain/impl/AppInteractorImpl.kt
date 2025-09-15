package ru.practicum.android.diploma.core.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.domain.AppInteractor
import ru.practicum.android.diploma.core.domain.repository.AppRepository
import ru.practicum.android.diploma.core.domain.repository.StorageKey
import ru.practicum.android.diploma.filtration.domain.model.FilterStorage

class AppInteractorImpl(
    val appRepository: AppRepository
) : AppInteractor {

    override fun <T : Any> saveData(key: StorageKey, data: T) {
        appRepository.saveData(key, data)
    }

    override suspend fun getData(key: StorageKey): String? {
        return appRepository.getData(key)
    }

    override suspend fun getAllData(): Flow<Map<String, String?>> {
        return appRepository.getAllData()
    }

    override suspend fun getAllDataWithNames(): Flow<FilterStorage> {
        return appRepository.getAllDataWithNames()
    }

    override fun clearByKey(key: StorageKey): Flow<FilterStorage> {
        return appRepository.clearByKey(key = key)
    }

    override fun clearStorage() {
        appRepository.clearStorage()
    }
}
