package ru.practicum.android.diploma.favorites.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.practicum.android.diploma.favorites.data.db.dao.FavoritesDao
import ru.practicum.android.diploma.favorites.data.mapper.Converters

@Database(
    version = 1,
    entities = [FavoritesEntity::class],
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao
}
