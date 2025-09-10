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
    fun FavoritesEntityToVacancyFavorites(favoritesEntity: FavoritesEntity?): VacancyFavorites? {
        return if (favoritesEntity != null) {
            VacancyFavorites(
                id = favoritesEntity.id,
                name = favoritesEntity.name,
                description = favoritesEntity.description,

                // Salary
                salaryFrom = favoritesEntity.salary?.from,
                salaryTo = favoritesEntity.salary?.to,
                salaryCurrency = favoritesEntity.salary?.currency.orEmpty(),

                // Address
                addressCity = favoritesEntity.address?.city.orEmpty(),
                addressStreet = favoritesEntity.address?.street.orEmpty(),
                addressBuilding = favoritesEntity.address?.building.orEmpty(),
                fullAddress = favoritesEntity.address?.fullAddress.orEmpty(),

                // Experience
                experienceId = favoritesEntity.experience?.id.orEmpty(),
                experienceName = favoritesEntity.experience?.name.orEmpty(),

                // Schedule
                scheduleId = favoritesEntity.schedule?.id.orEmpty(),
                scheduleName = favoritesEntity.schedule?.name.orEmpty(),

                // Employment
                employmentId = favoritesEntity.employment?.id.orEmpty(),
                employmentName = favoritesEntity.employment?.name.orEmpty(),

                // Contacts
                contactsId = favoritesEntity.contacts?.id.orEmpty(),
                contactsName = favoritesEntity.contacts?.name.orEmpty(),
                contactsEmail = favoritesEntity.contacts?.email.orEmpty(),
                contactsPhone = favoritesEntity.contacts?.phone.orEmpty(),

                // Employer
                employerId = favoritesEntity.employer?.id.orEmpty(),
                employerName = favoritesEntity.employer?.name.orEmpty(),
                employerLogo = favoritesEntity.employer?.logo.orEmpty(),

                // Area
                areaId = favoritesEntity.area?.id ?: 0,
                areaName = favoritesEntity.area?.name.orEmpty(),
                areaParentId = favoritesEntity.area?.parentId ?: 0,

                // Skills
                skills = favoritesEntity.skills.orEmpty(),

                // URL
                url = favoritesEntity.url.orEmpty(),

                // Industry
                industryId = favoritesEntity.industry?.id ?: 0,
                industryName = favoritesEntity.industry?.name.orEmpty()
            )
        } else null
    }

    fun vacancyDetailToFavoritesEntity(vacancyDetail: VacancyDetail): FavoritesEntity {
        return FavoritesEntity(
            id = vacancyDetail.id,
            name = vacancyDetail.name,
            description = vacancyDetail.description,
            salary = vacancyDetail.salary?.let {
                SalaryEntity(it.from, it.to, it.currency)
            },
            address = vacancyDetail.address?.let {
                AddressEntity(it.city, it.street, it.building, it.fullAddress)
            },
            experience = vacancyDetail.experience?.let {
                ExperienceEntity(it.id, it.name)
            },
            schedule = vacancyDetail.schedule?.let {
                ScheduleEntity(it.id, it.name)
            },
            employment = vacancyDetail.employment?.let {
                EmploymentEntity(it.id, it.name)
            },
            contacts = vacancyDetail.contacts?.let {
                ContactsEntity(it.id, it.name, it.email, it.phone)
            },
            employer = vacancyDetail.employer?.let {
                EmployerEntity(it.id, it.name, it.logo)
            },
            area = vacancyDetail.area?.let {
                FilterAreaEntity(it.id, it.name, it.parentId)
            },
            skills = vacancyDetail.skills,
            url = vacancyDetail.url,
            industry = vacancyDetail.industry?.let {
                IndustryEntity(it.id, it.name)
            },
            addedAt = System.currentTimeMillis()
        )
    }

    fun vacancyFavoritesToVacancyDetail(entity: VacancyFavorites?): VacancyDetail? {
        return if (entity != null) {
            VacancyDetail(
                id = entity.id,
                name = entity.name,
                description = entity.description,
                salary = Salary(
                    from = entity.salaryFrom,
                    to = entity.salaryTo,
                    currency = entity.salaryCurrency
                ),
                address = Address(
                    fullAddress = entity.fullAddress,
                    city = entity.addressCity,
                    street = entity.addressStreet,
                    building = entity.addressBuilding
                ),
                experience = Experience(
                    id = entity.experienceId,
                    name = entity.experienceName
                ),
                schedule = Schedule(
                    id = entity.scheduleId,
                    name = entity.scheduleName
                ),
                employment = Employment(
                    id = entity.employmentId,
                    name = entity.employmentName
                ),
                contacts = Contacts(
                    id = entity.contactsId,
                    name = entity.contactsName,
                    email = entity.contactsEmail,
                    phone = entity.contactsPhone
                ),
                employer = Employer(
                    id = entity.employerId,
                    name = entity.employerName,
                    logo = entity.employerLogo
                ),
                area = FilterAreas(
                    id = entity.areaId,
                    name = entity.areaName,
                    parentId = entity.areaParentId,
                    areas = emptyList()
                ),
                skills = entity.skills,
                url = entity.url,
                industry = FilterIndustryDetail(
                    id = entity.industryId,
                    name = entity.industryName
                ),
            )
        } else null
    }
}
