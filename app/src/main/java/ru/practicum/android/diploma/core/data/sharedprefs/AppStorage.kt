package ru.practicum.android.diploma.core.data.sharedprefs

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.domain.repository.StorageKey
import ru.practicum.android.diploma.filtration.domain.model.FilterStorage
import kotlin.String

class AppStorage(
    val sharedPrefs: SharedPreferences,
) {
    private var _storageState = MutableStateFlow(FilterStorage())
    val storageState = _storageState.asStateFlow()

    private fun getMapWithKeys(): Map<String, String?> {
        val area = getStorageByKey(StorageKey.AREA_ID_KEY)
        val salary = getStorageByKey(StorageKey.SALARY_ID_KEY)
        val onlyWithSalary = getStorageByKey(StorageKey.ONLY_WITH_SALARY_KEY)
        val industry = getStorageByKey(StorageKey.INDUSTRY_ID_KEY)

        return mapOf(
            StorageKey.AREA_ID_KEY.key to area,
            StorageKey.SALARY_ID_KEY.key to salary,
            StorageKey.ONLY_WITH_SALARY_KEY.key to onlyWithSalary,
            StorageKey.INDUSTRY_ID_KEY.key to industry,
        )
    }

    private fun saveStorageByKey(key: String, value: String) {
        sharedPrefs
            .edit {
                putString(key, value)
            }
    }

    fun <T : Any> saveStorage(key: StorageKey, data: T) {
        //clearByKey(key)
        val value = data.toString()
        saveStorageByKey(key.key, value)
        val newStorage = when (key) {
            StorageKey.AREA_ID_KEY -> _storageState.value.copy(areaId = value)
            StorageKey.AREA_NAME_KEY -> _storageState.value.copy(areaValue = value)
            StorageKey.SALARY_ID_KEY -> _storageState.value.copy(salaryId = value)
            StorageKey.SALARY_NAME_KEY -> _storageState.value.copy(salaryValue = value)
            StorageKey.INDUSTRY_ID_KEY -> _storageState.value.copy(industryId = value)
            StorageKey.INDUSTRY_NAME_KEY -> _storageState.value.copy(industryValue = value)
            StorageKey.ONLY_WITH_SALARY_KEY -> _storageState.value.copy(withSalary = value)
        }
        _storageState.value = newStorage
    }

    fun getStorageByKey(key: StorageKey): String? {
        return sharedPrefs.getString(key.key, null)
    }

    fun getData(): Flow<Map<String, String?>> = flow {
        emit(
            getMapWithKeys()
        )
    }

    fun getDataWithNames(): Flow<FilterStorage> {
        val area = getStorageByKey(StorageKey.AREA_ID_KEY)
        val salary = getStorageByKey(StorageKey.SALARY_ID_KEY)
        val onlyWithSalary = getStorageByKey(StorageKey.ONLY_WITH_SALARY_KEY)
        val industry = getStorageByKey(StorageKey.INDUSTRY_ID_KEY)
        val industryValue = getStorageByKey(StorageKey.INDUSTRY_NAME_KEY)
        _storageState.value.copy(
            industryValue = industryValue ?: "",
            industryId = industry ?: "",
            areaId = area ?: "",
            salaryValue = salary ?: "",
            withSalary = onlyWithSalary ?: ""
        )
        return storageState
    }

    fun clearByKey(key: StorageKey): Flow<FilterStorage> {
        val newStorage = when (key) {
            StorageKey.AREA_ID_KEY, StorageKey.AREA_NAME_KEY -> {
                sharedPrefs
                    .edit {
                        remove(StorageKey.AREA_ID_KEY.key)
                        remove(StorageKey.AREA_NAME_KEY.key)
                    }
                _storageState.value.copy(
                    areaId = "",
                    areaValue = ""
                )
            }

            StorageKey.SALARY_ID_KEY, StorageKey.SALARY_NAME_KEY -> {
                sharedPrefs
                    .edit {
                        remove(StorageKey.SALARY_ID_KEY.key)
                        remove(StorageKey.SALARY_NAME_KEY.key)
                    }
                _storageState.value.copy(
                    salaryId = "",
                    salaryValue = ""
                )
            }

            StorageKey.INDUSTRY_ID_KEY, StorageKey.INDUSTRY_NAME_KEY -> {
                sharedPrefs
                    .edit {
                        remove(StorageKey.INDUSTRY_ID_KEY.key)
                        remove(StorageKey.INDUSTRY_NAME_KEY.key)
                    }
                _storageState.value.copy(
                    industryId = "",
                    industryValue = ""
                )
            }

            StorageKey.ONLY_WITH_SALARY_KEY -> {
                sharedPrefs
                    .edit {
                        remove(StorageKey.ONLY_WITH_SALARY_KEY.key)
                    }
                _storageState.value.copy(
                    withSalary = ""
                )
            }
        }
        _storageState.value = newStorage
        return storageState
    }

    fun clearStorage() {
        sharedPrefs
            .edit {
                clear()
            }
        val newStorage = _storageState.value.copy(
            areaId = "",
            areaValue = "",
            industryId = "",
            industryValue = "",
            salaryId = "",
            salaryValue = "",
            withSalary = "",
        )
        _storageState.value = newStorage
        return
    }
}
