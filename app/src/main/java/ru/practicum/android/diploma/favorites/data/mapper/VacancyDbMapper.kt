package ru.practicum.android.diploma.favorites.data.mapper

import ru.practicum.android.diploma.core.data.dto.FilterAreas
import ru.practicum.android.diploma.core.data.dto.FilterIndustryDetail
import ru.practicum.android.diploma.core.data.dto.VacancyDetailDto
import ru.practicum.android.diploma.core.data.dto.vacancydetails.Address
import ru.practicum.android.diploma.core.data.dto.vacancydetails.Contacts
import ru.practicum.android.diploma.core.data.dto.vacancydetails.Employer
import ru.practicum.android.diploma.core.data.dto.vacancydetails.Employment
import ru.practicum.android.diploma.core.data.dto.vacancydetails.Experience
import ru.practicum.android.diploma.core.data.dto.vacancydetails.Salary
import ru.practicum.android.diploma.core.data.dto.vacancydetails.Schedule
import ru.practicum.android.diploma.favorites.data.db.FavoritesEntity
import ru.practicum.android.diploma.favorites.data.model.entity.AddressEntity
import ru.practicum.android.diploma.favorites.data.model.entity.ContactsEntity
import ru.practicum.android.diploma.favorites.data.model.entity.EmployerEntity
import ru.practicum.android.diploma.favorites.data.model.entity.EmploymentEntity
import ru.practicum.android.diploma.favorites.data.model.entity.ExperienceEntity
import ru.practicum.android.diploma.favorites.data.model.entity.FilterAreaEntity
import ru.practicum.android.diploma.favorites.data.model.entity.IndustryEntity
import ru.practicum.android.diploma.favorites.data.model.entity.SalaryEntity
import ru.practicum.android.diploma.favorites.data.model.entity.ScheduleEntity
import ru.practicum.android.diploma.favorites.domain.model.VacancyFavorites
import ru.practicum.android.diploma.vacancy.domain.model.VacancyDetail

object VacancyDbMapper {

    fun favoritesEntityToVacancyFavorites(favoritesEntity: FavoritesEntity?): VacancyFavorites? {
        return favoritesEntity?.let {
            VacancyFavorites(
                id = it.id,
                name = it.name,
                description = it.description,
                salaryFrom = it.salary?.from,
                salaryTo = it.salary?.to,
                salaryCurrency = it.salary?.currency,
                addressCity = it.address?.city.orEmpty(),
                addressStreet = it.address?.street.orEmpty(),
                addressBuilding = it.address?.building.orEmpty(),
                fullAddress = it.address?.fullAddress.orEmpty(),
                experienceId = it.experience?.id.orEmpty(),
                experienceName = it.experience?.name.orEmpty(),
                scheduleId = it.schedule?.id.orEmpty(),
                scheduleName = it.schedule?.name.orEmpty(),
                employmentId = it.employment?.id.orEmpty(),
                employmentName = it.employment?.name.orEmpty(),
                contactsId = it.contacts?.id.orEmpty(),
                contactsName = it.contacts?.name.orEmpty(),
                contactsEmail = it.contacts?.email.orEmpty(),
                contactsPhone = it.contacts?.phone,
                employerId = it.employer?.id.orEmpty(),
                employerName = it.employer?.name.orEmpty(),
                employerLogo = it.employer?.logo,
                areaId = it.area?.id ?: 0,
                areaName = it.area?.name.orEmpty(),
                areaParentId = it.area?.parentId ?: 0,
                skills = it.skills.orEmpty(),
                url = it.url.orEmpty(),
                industryId = it.industry?.id ?: 0,
                industryName = it.industry?.name.orEmpty()
            )
        }
    }

    // dto -> entity
    fun vacancyDetailToFavoritesEntity(dto: VacancyDetailDto): FavoritesEntity {
        return FavoritesEntity(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            salary = mapSalary(dto.salary),
            address = mapAddress(dto.address),
            experience = mapExperience(dto.experience),
            schedule = mapSchedule(dto.schedule),
            employment = mapEmployment(dto.employment),
            contacts = mapContacts(dto.contacts),
            employer = mapEmployer(dto.employer),
            area = mapArea(dto.area),
            skills = dto.skills,
            url = dto.url,
            industry = mapIndustry(dto.industry)
        )
    }

    // domain -> dto
    fun detailToDto(detail: VacancyDetail): VacancyDetailDto {
        return VacancyDetailDto(
            id = detail.id,
            name = detail.name,
            description = detail.description,
            salary = Salary(detail.salaryFrom, detail.salaryTo, detail.salaryCurrency),
            address = Address(detail.addressCity, detail.addressStreet, detail.addressBuilding, detail.fullAddress),
            experience = Experience(detail.experienceId, detail.experienceName),
            schedule = Schedule(detail.scheduleId, detail.scheduleName),
            employment = Employment(detail.employmentId, detail.employmentName),
            contacts = Contacts(
                detail.contactsId,
                detail.contactsName,
                detail.contactsEmail,
                detail.contactsPhone
            ),
            employer = Employer(detail.employerId, detail.employerName, detail.employerLogo),
            area = FilterAreas(detail.areaId, detail.areaName, detail.areaParentId, emptyList()),
            skills = detail.skills,
            url = detail.url,
            industry = FilterIndustryDetail(detail.industryId, detail.industryName)
        )
    }

    private fun mapSalary(salary: Salary) = SalaryEntity(salary.from, salary.to, salary.currency)
    private fun mapAddress(address: Address) =
        AddressEntity(address.city, address.street, address.building, address.raw)

    private fun mapExperience(exp: Experience) = ExperienceEntity(exp.id, exp.name)
    private fun mapSchedule(schedule: Schedule) = ScheduleEntity(schedule.id, schedule.name)
    private fun mapEmployment(emp: Employment) = EmploymentEntity(emp.id, emp.name)
    private fun mapContacts(contacts: Contacts) =
        ContactsEntity(contacts.id, contacts.name, contacts.email, contacts.phone)

    private fun mapEmployer(emp: Employer) = EmployerEntity(emp.id, emp.name, emp.logo)
    private fun mapArea(area: FilterAreas) = FilterAreaEntity(area.id, area.name, area.parentId)
    private fun mapIndustry(ind: FilterIndustryDetail) = IndustryEntity(ind.id, ind.name)
}
