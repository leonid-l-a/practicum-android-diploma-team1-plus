package ru.practicum.android.diploma.core.data.dto

import ru.practicum.android.diploma.core.data.dto.vacancyDetails.Address
import ru.practicum.android.diploma.core.data.dto.vacancyDetails.Contacts
import ru.practicum.android.diploma.core.data.dto.vacancyDetails.Employment
import ru.practicum.android.diploma.core.data.dto.vacancyDetails.Experience
import ru.practicum.android.diploma.core.data.dto.vacancyDetails.Salary
import ru.practicum.android.diploma.core.data.dto.vacancyDetails.Schedule
import ru.practicum.android.diploma.core.data.dto.vacancyDetails.Employer
import ru.practicum.android.diploma.core.data.dto.vacancyDetails.FilterArea

data class VacancyDetailResponse(
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
    val industry: FilterIndustryResponse
) : Response()
