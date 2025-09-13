package ru.practicum.android.diploma.core.domain

interface AppStorageInteractor {
    fun saveArea(area: Int)

    suspend fun getArea(): String?

    fun saveSalary(salary: Int)

    suspend fun getSalary(): String?

    fun saveOnlyWithSalary(onlyWithSalary: Boolean)

    suspend fun getOnlyWithSalary(): String?

    suspend fun getData(): Map<String, String?>
}
