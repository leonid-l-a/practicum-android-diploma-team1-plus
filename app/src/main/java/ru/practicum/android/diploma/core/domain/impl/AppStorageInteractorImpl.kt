package ru.practicum.android.diploma.core.domain.impl

import ru.practicum.android.diploma.core.domain.AppStorageInteractor
import ru.practicum.android.diploma.core.domain.repository.AppStorageRepository

class AppStorageInteractorImpl(
    val appStorageRepository: AppStorageRepository
) : AppStorageInteractor {
    override fun saveArea(area: Int) {
        appStorageRepository.saveArea(area)
    }

    override suspend fun getArea(): String? {
        return appStorageRepository.getArea()
    }

    override fun saveSalary(salary: Int) {}

    override suspend fun getSalary(): String? {
        return appStorageRepository.getSalary()
    }

    override fun saveOnlyWithSalary(onlyWithSalary: Boolean) {}

    override suspend fun getOnlyWithSalary(): String? {
        return appStorageRepository.getOnlyWithSalary()
    }

    override suspend fun getData(): Map<String, String?> {
        return appStorageRepository.getData()
    }
}
