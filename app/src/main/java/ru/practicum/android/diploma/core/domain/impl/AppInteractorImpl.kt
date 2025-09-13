package ru.practicum.android.diploma.core.domain.impl

import ru.practicum.android.diploma.core.domain.AppInteractor
import ru.practicum.android.diploma.core.domain.repository.AppRepository

class AppInteractorImpl(
    val appRepository: AppRepository
) : AppInteractor {
    override fun saveArea(area: Int) {
        appRepository.saveArea(area)
    }

    override suspend fun getArea(): String? {
        return appRepository.getArea()
    }

    override fun saveSalary(salary: Int) {
        appRepository.saveSalary(salary)
    }

    override suspend fun getSalary(): String? {
        return appRepository.getSalary()
    }

    override fun saveOnlyWithSalary(onlyWithSalary: Boolean) {
        appRepository.saveOnlyWithSalary(onlyWithSalary)
    }

    override suspend fun getOnlyWithSalary(): String? {
        return appRepository.getOnlyWithSalary()
    }

    override suspend fun getData(): Map<String, String?> {
        return appRepository.getData()
    }
}
