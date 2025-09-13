package ru.practicum.android.diploma.core.data.repository

import ru.practicum.android.diploma.core.data.sharedprefs.AppStorage
import ru.practicum.android.diploma.core.data.sharedprefs.AppStorage.StorageKey
import ru.practicum.android.diploma.core.domain.repository.AppRepository

class AppRepositoryImpl(
    val appStorage: AppStorage
) : AppRepository {

    override fun saveIndustry(industry: Int) {
        appStorage.saveStorage(StorageKey.IDUSTRY, industry)
    }

    override fun getIndustry(): String? {
        return appStorage.getStorageByKey(StorageKey.IDUSTRY)
    }

    override fun saveArea(area: Int) {
        appStorage.saveStorage(StorageKey.AREA_KEY, area)
    }

    override fun getArea(): String? {
        return appStorage.getStorageByKey(StorageKey.AREA_KEY)
    }

    override fun saveSalary(salary: Int) {
        appStorage.saveStorage(StorageKey.SALARY_KEY, salary)
    }

    override fun getSalary(): String? {
        return appStorage.getStorageByKey(StorageKey.SALARY_KEY)
    }

    override fun saveOnlyWithSalary(onlyWithSalary: Boolean) {
        appStorage.saveStorage(StorageKey.ONLY_WITH_SALARY_KEY, onlyWithSalary)
    }

    override fun getOnlyWithSalary(): String? {
        return appStorage.getStorageByKey(StorageKey.ONLY_WITH_SALARY_KEY)
    }

    override fun getData(): Map<String, String?> {
        return appStorage.getData()
    }
}
