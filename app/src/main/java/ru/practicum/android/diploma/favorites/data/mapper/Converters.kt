package ru.practicum.android.diploma.favorites.data.mapper

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object Converters {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun fromStringList(list: List<String>?): String? =
        list?.let { gson.toJson(it) }

    @TypeConverter
    @JvmStatic
    fun toStringList(value: String?): List<String> =
        if (value.isNullOrEmpty()) emptyList()
        else gson.fromJson(value, object : TypeToken<List<String>>() {}.type)
}
