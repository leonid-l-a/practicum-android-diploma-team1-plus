package ru.practicum.android.diploma.favorites.data.mapper

import ru.practicum.android.diploma.core.data.dto.FilterAreas
import ru.practicum.android.diploma.core.data.dto.FilterIndustryDetail
import ru.practicum.android.diploma.core.data.dto.VacancyDetail
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

object VacancyDbMapper {
    fun favoritesEntityToVacancyFavorites(favoritesEntity: FavoritesEntity?): VacancyFavorites? {
        return if (favoritesEntity != null) {
            VacancyFavorites(
                id = favoritesEntity.id,
                name = favoritesEntity.name,
                description = favoritesEntity.description,
                salaryFrom = favoritesEntity.salary?.from,
                salaryTo = favoritesEntity.salary?.to,
                salaryCurrency = favoritesEntity.salary?.currency.orEmpty(),
                addressCity = favoritesEntity.address?.city.orEmpty(),
                addressStreet = favoritesEntity.address?.street.orEmpty(),
                addressBuilding = favoritesEntity.address?.building.orEmpty(),
                fullAddress = favoritesEntity.address?.fullAddress.orEmpty(),
                experienceId = favoritesEntity.experience?.id.orEmpty(),
                experienceName = favoritesEntity.experience?.name.orEmpty(),
                scheduleId = favoritesEntity.schedule?.id.orEmpty(),
                scheduleName = favoritesEntity.schedule?.name.orEmpty(),
                employmentId = favoritesEntity.employment?.id.orEmpty(),
                employmentName = favoritesEntity.employment?.name.orEmpty(),
                contactsId = favoritesEntity.contacts?.id.orEmpty(),
                contactsName = favoritesEntity.contacts?.name.orEmpty(),
                contactsEmail = favoritesEntity.contacts?.email.orEmpty(),
                contactsPhone = favoritesEntity.contacts?.phone.orEmpty(),
                employerId = favoritesEntity.employer?.id.orEmpty(),
                employerName = favoritesEntity.employer?.name.orEmpty(),
                employerLogo = favoritesEntity.employer?.logo.orEmpty(),
                areaId = favoritesEntity.area?.id ?: 0,
                areaName = favoritesEntity.area?.name.orEmpty(),
                areaParentId = favoritesEntity.area?.parentId ?: 0,
                skills = favoritesEntity.skills.orEmpty(),
                url = favoritesEntity.url.orEmpty(),
                industryId = favoritesEntity.industry?.id ?: 0,
                industryName = favoritesEntity.industry?.name.orEmpty()
            )
        } else {
            null
        }
    }

    fun vacancyDetailToFavoritesEntity(vacancyDetail: VacancyDetail): FavoritesEntity {
        return FavoritesEntity(
            id = vacancyDetail.id,
            name = vacancyDetail.name,
            description = vacancyDetail.description,
            salary = mapSalary(vacancyDetail.salary),
            address = mapAddress(vacancyDetail.address),
            experience = mapExperience(vacancyDetail.experience),
            schedule = mapSchedule(vacancyDetail.schedule),
            employment = mapEmployment(vacancyDetail.employment),
            contacts = mapContacts(vacancyDetail.contacts),
            employer = mapEmployer(vacancyDetail.employer),
            area = mapArea(vacancyDetail.area),
            skills = vacancyDetail.skills,
            url = vacancyDetail.url,
            industry = mapIndustry(vacancyDetail.industry)
        )
    }

    private fun mapSalary(salary: Salary): SalaryEntity =
        SalaryEntity(salary.from, salary.to, salary.currency)

    private fun mapAddress(address: Address): AddressEntity =
        AddressEntity(address.city, address.street, address.building, address.fullAddress)

    private fun mapExperience(exp: Experience): ExperienceEntity =
        ExperienceEntity(exp.id, exp.name)

    private fun mapSchedule(schedule: Schedule): ScheduleEntity =
        ScheduleEntity(schedule.id, schedule.name)

    private fun mapEmployment(emp: Employment): EmploymentEntity =
        EmploymentEntity(emp.id, emp.name)

    private fun mapContacts(contacts: Contacts): ContactsEntity =
        ContactsEntity(contacts.id, contacts.name, contacts.email, contacts.phone)

    private fun mapEmployer(emp: Employer): EmployerEntity =
        EmployerEntity(emp.id, emp.name, emp.logo)

    private fun mapArea(area: FilterAreas): FilterAreaEntity =
        FilterAreaEntity(area.id, area.name, area.parentId)

    private fun mapIndustry(ind: FilterIndustryDetail): IndustryEntity =
        IndustryEntity(ind.id, ind.name)

    fun vacancyFavoritesToVacancyDetail(entity: VacancyFavorites?): VacancyDetail? {
        return entity?.let {
            VacancyDetail(
                id = it.id,
                name = it.name,
                description = it.description,
                salary = mapSalary(it),
                address = mapAddress(it),
                experience = mapExperience(it),
                schedule = mapSchedule(it),
                employment = mapEmployment(it),
                contacts = mapContacts(it),
                employer = mapEmployer(it),
                area = mapArea(it),
                skills = it.skills,
                url = it.url,
                industry = mapIndustry(it)
            )
        }
    }

    private fun mapSalary(entity: VacancyFavorites) =
        Salary(
            from = entity.salaryFrom,
            to = entity.salaryTo,
            currency = entity.salaryCurrency
        )

    private fun mapAddress(entity: VacancyFavorites) =
        Address(
            fullAddress = entity.fullAddress,
            city = entity.addressCity,
            street = entity.addressStreet,
            building = entity.addressBuilding
        )

    private fun mapExperience(entity: VacancyFavorites) =
        Experience(
            id = entity.experienceId,
            name = entity.experienceName
        )

    private fun mapSchedule(entity: VacancyFavorites) =
        Schedule(
            id = entity.scheduleId,
            name = entity.scheduleName
        )

    private fun mapEmployment(entity: VacancyFavorites) =
        Employment(
            id = entity.employmentId,
            name = entity.employmentName
        )

    private fun mapContacts(entity: VacancyFavorites) =
        Contacts(
            id = entity.contactsId,
            name = entity.contactsName,
            email = entity.contactsEmail,
            phone = entity.contactsPhone
        )

    private fun mapEmployer(entity: VacancyFavorites) =
        Employer(id = entity.employerId, name = entity.employerName, logo = entity.employerLogo)

    private fun mapArea(entity: VacancyFavorites) =
        FilterAreas(
            id = entity.areaId,
            name = entity.areaName,
            parentId = entity.areaParentId,
            areas = emptyList()
        )

    private fun mapIndustry(entity: VacancyFavorites) =
        FilterIndustryDetail(id = entity.industryId, name = entity.industryName)

}
