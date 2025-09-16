package ru.practicum.android.diploma.core.data.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.data.sharedprefs.AppStorage
import ru.practicum.android.diploma.core.domain.repository.AppRepository
import ru.practicum.android.diploma.core.domain.repository.StorageKey
import ru.practicum.android.diploma.filtration.domain.model.FilterStorage

class AppRepositoryImpl(
    val appStorage: AppStorage
) : AppRepository {

    override fun <T : Any> saveData(key: StorageKey, data: T) {
        appStorage.saveStorage(key, data)
    }

    override suspend fun getData(key: StorageKey): String? {
        return appStorage.getStorageByKey(key)
    }

    override suspend fun getAllData(): Flow<Map<String, String?>> {
        return appStorage.getData()
    }

    override suspend fun getAllDataWithNames(): Flow<FilterStorage> {
        return appStorage.getDataWithNames()
    }

    override fun clearByKey(key: StorageKey): Flow<FilterStorage> {
        return appStorage.clearByKey(key)
    }

    override fun clearStorage() {
        appStorage.clearStorage()
    }
}
