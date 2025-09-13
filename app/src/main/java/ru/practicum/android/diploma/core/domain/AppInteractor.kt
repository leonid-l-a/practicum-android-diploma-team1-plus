package ru.practicum.android.diploma.core.domain

interface AppInteractor {
    fun saveIndustry(industry: Int)

    fun getIndustry(): String?

    fun saveArea(area: Int)

    fun getArea(): String?

    fun saveSalary(salary: Int)

    fun getSalary(): String?

    fun saveOnlyWithSalary(onlyWithSalary: Boolean)

    fun getOnlyWithSalary(): String?

    fun getData(): Map<String, String?>
}
