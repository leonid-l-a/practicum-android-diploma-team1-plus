package ru.practicum.android.diploma.main.data.mapper

import ru.practicum.android.diploma.core.data.dto.Vacancy
import ru.practicum.android.diploma.core.data.dto.vacancydetails.Employer
import ru.practicum.android.diploma.core.data.dto.vacancydetails.Salary
import ru.practicum.android.diploma.main.data.model.EmployerMainData
import ru.practicum.android.diploma.main.data.model.SalaryMainData
import ru.practicum.android.diploma.main.data.model.VacancyDetailMainData
import ru.practicum.android.diploma.main.data.model.VacancyMainData

object VacancyToVacancyMainData {
    fun vacancyToVacancyMainData(vacancy: Vacancy): VacancyMainData {
        return VacancyMainData(
            found = vacancy.found,
            pages = vacancy.pages,
            page = vacancy.page,
            items = vacancy.items.map { detail ->
                VacancyDetailMainData(
                    id = detail.id,
                    name = detail.name,
                    salary = salary(detail.salary),
                    employer = employer(detail.employer)
                )
            }
        )
    }

    private fun salary(salary: Salary): SalaryMainData {
        return SalaryMainData(
            from = salary.from,
            to = salary.to,
            currency = salary.currency
        )
    }

    private fun employer(employer: Employer): EmployerMainData {
        return EmployerMainData(
            id = employer.id,
            name = employer.name,
            logo = employer.logo
        )
    }
}

