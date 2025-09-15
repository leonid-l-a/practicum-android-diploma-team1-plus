package ru.practicum.android.diploma.core.data.sharedprefs

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.domain.repository.StorageKey

class AppStorage(
    val sharedPrefs: SharedPreferences,
) {
    fun <T : Any> saveStorage(key: StorageKey, data: T) {
        clearStorageByKey(key.key)
        saveStorageByKey(key.key, data.toString())
    }

    suspend fun getStorageByKey(key: StorageKey): String? {
        return sharedPrefs.getString(key.key, null)
    }

    fun getData(): Flow<Map<String, String?>> = flow {
        val area = getStorageByKey(StorageKey.AREA_ID_KEY)
        val salary = getStorageByKey(StorageKey.SALARY_ID_KEY)
        val onlyWithSalary = getStorageByKey(StorageKey.ONLY_WITH_SALARY_KEY)
        val industry = getStorageByKey(StorageKey.INDUSTRY_ID_KEY)
        emit(
            mapOf(
                StorageKey.AREA_ID_KEY.key to area,
                StorageKey.SALARY_ID_KEY.key to salary,
                StorageKey.ONLY_WITH_SALARY_KEY.key to onlyWithSalary,
                StorageKey.INDUSTRY_ID_KEY.key to industry,
            )
        )
    }

    private fun saveStorageByKey(key: String, value: String) {
        sharedPrefs
            .edit {
                putString(key, value)
            }
    }

    private fun clearStorageByKey(key: String) {
        sharedPrefs
            .edit {
                remove(key)
            }
    }

    fun clearStorage() {
        sharedPrefs
            .edit {
                clear()
            }
    }
}
