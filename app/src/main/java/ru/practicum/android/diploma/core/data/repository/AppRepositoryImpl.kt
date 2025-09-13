package ru.practicum.android.diploma.core.data.repository

import ru.practicum.android.diploma.core.data.sharedprefs.AppStorage
import ru.practicum.android.diploma.core.data.sharedprefs.AppStorage.StorageKey
import ru.practicum.android.diploma.core.domain.repository.AppRepository

class AppRepositoryImpl(
    val appStorage: AppStorage
) : AppRepository {

    override fun saveArea(area: Int) {
        appStorage.saveStorage(StorageKey.AREA_KEY, area)
    }

    override suspend fun getArea(): String? {
        return appStorage.getStorageByKey(StorageKey.AREA_KEY)
    }

    override fun saveSalary(salary: Int) {
        appStorage.saveStorage(StorageKey.SALARY_KEY, salary)
    }

    override suspend fun getSalary(): String? {
        return appStorage.getStorageByKey(StorageKey.SALARY_KEY)
    }

    override fun saveOnlyWithSalary(onlyWithSalary: Boolean) {
        appStorage.saveStorage(StorageKey.ONLY_WITH_SALARY_KEY, onlyWithSalary)
    }

    override suspend fun getOnlyWithSalary(): String? {
        return appStorage.getStorageByKey(StorageKey.ONLY_WITH_SALARY_KEY)
    }

    override suspend fun getData(): Map<String, String?> {
        return appStorage.getData()
    }
}
