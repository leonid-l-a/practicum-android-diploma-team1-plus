package ru.practicum.android.diploma.core.domain.impl

import ru.practicum.android.diploma.core.domain.AppInteractor
import ru.practicum.android.diploma.core.domain.repository.AppRepository

class AppInteractorImpl(
    val appRepository: AppRepository
) : AppInteractor {
    override fun saveIndustry(industry: Int) {
        appRepository.saveIndustry(industry)
    }

    override fun getIndustry(): String? {
        return appRepository.getIndustry()
    }

    override fun saveArea(area: Int) {
        appRepository.saveArea(area)
    }

    override fun getArea(): String? {
        return appRepository.getArea()
    }

    override fun saveSalary(salary: Int) {
        appRepository.saveSalary(salary)
    }

    override fun getSalary(): String? {
        return appRepository.getSalary()
    }

    override fun saveOnlyWithSalary(onlyWithSalary: Boolean) {
        appRepository.saveOnlyWithSalary(onlyWithSalary)
    }

    override fun getOnlyWithSalary(): String? {
        return appRepository.getOnlyWithSalary()
    }

    override fun getData(): Map<String, String?> {
        return appRepository.getData()
    }
}
