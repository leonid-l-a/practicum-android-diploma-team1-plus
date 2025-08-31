package ru.practicum.android.diploma.core.data.dto

import ru.practicum.android.diploma.core.data.dto.vacancy_details.Address
import ru.practicum.android.diploma.core.data.dto.vacancy_details.Contacts
import ru.practicum.android.diploma.core.data.dto.vacancy_details.Employment
import ru.practicum.android.diploma.core.data.dto.vacancy_details.Experience
import ru.practicum.android.diploma.core.data.dto.vacancy_details.Salary
import ru.practicum.android.diploma.core.data.dto.vacancy_details.Schedule
import ru.practicum.android.diploma.core.data.dto.vacancy_details.Employer
import ru.practicum.android.diploma.core.data.dto.vacancy_details.FilterArea

data class VacancyDetailResponce(
    val id: Int,
    val name: String,
    val description: String,
    val salary: Salary,
    val address: Address,
    val experience: Experience,
    val schedule: Schedule,
    val employment: Employment,
    val contacts: Contacts,
    val employer: Employer,
    val area: FilterArea,
    val skills: List<String>,
    val url: String,
    val industry: FilterIndustryResponce
) : Responce()
