package ru.practicum.android.diploma.core.data.sharedprefs

import android.content.SharedPreferences
import androidx.core.content.edit

class AppStorage(
    val sharedPrefs: SharedPreferences,
) {

    enum class StorageKey(val key: String) {
        AREA_KEY("area_key"),
        SALARY_KEY("salary_key"),
        IDUSTRY("industry_key"),
        ONLY_WITH_SALARY_KEY("only_with_salary_key")
    }

    fun <T : Any> saveStorage(key: StorageKey, data: T) {
        clearStorageByKey(key.key)
        saveStorageByKey(key.key, data.toString())
    }

    fun getStorageByKey(key: StorageKey): String? {
        return sharedPrefs.getString(key.key, null)
    }

    fun getData(): Map<String, String?> {
        return mapOf(
            "area" to getStorageByKey(StorageKey.AREA_KEY),
            "salary" to getStorageByKey(StorageKey.SALARY_KEY),
            "onlyWithSalary" to getStorageByKey(StorageKey.ONLY_WITH_SALARY_KEY),
            "industry" to getStorageByKey(StorageKey.IDUSTRY),
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
}
