package ru.practicum.android.diploma.favorites.data.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.practicum.android.diploma.favorites.data.model.entity.AddressEntity
import ru.practicum.android.diploma.favorites.data.model.entity.ContactsEntity
import ru.practicum.android.diploma.favorites.data.model.entity.EmployerEntity
import ru.practicum.android.diploma.favorites.data.model.entity.EmploymentEntity
import ru.practicum.android.diploma.favorites.data.model.entity.ExperienceEntity
import ru.practicum.android.diploma.favorites.data.model.entity.FilterAreaEntity
import ru.practicum.android.diploma.favorites.data.model.entity.IndustryEntity
import ru.practicum.android.diploma.favorites.data.model.entity.SalaryEntity
import ru.practicum.android.diploma.favorites.data.model.entity.ScheduleEntity

@Entity(tableName = "favorites_vacancy_table")
data class FavoritesEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    @Embedded(prefix = "salary_") val salary: SalaryEntity?,
    @Embedded(prefix = "address_") val address: AddressEntity?,
    @Embedded(prefix = "experience_") val experience: ExperienceEntity?,
    @Embedded(prefix = "schedule_") val schedule: ScheduleEntity?,
    @Embedded(prefix = "employment_") val employment: EmploymentEntity?,
    @Embedded(prefix = "contacts_") val contacts: ContactsEntity?,
    @Embedded(prefix = "employer_") val employer: EmployerEntity?,
    @Embedded(prefix = "area_") val area: FilterAreaEntity?,
    val skills: List<String>?,
    val url: String?,
    @Embedded(prefix = "industry_") val industry: IndustryEntity?,
    @ColumnInfo(name = "added_at") val addedAt: Long = System.currentTimeMillis()
)
