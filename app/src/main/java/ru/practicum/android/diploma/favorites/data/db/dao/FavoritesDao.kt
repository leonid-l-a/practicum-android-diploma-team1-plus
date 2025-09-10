package ru.practicum.android.diploma.favorites.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.favorites.data.db.FavoritesEntity

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteVacancy(vacancy: FavoritesEntity): Long

    @Query("DELETE FROM favorites_vacancy_table WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM favorites_vacancy_table ORDER BY added_at DESC")
    fun getAllFavoritesVacancy(): Flow<List<FavoritesEntity>>

    @Query("SELECT * FROM favorites_vacancy_table WHERE id = :id")
    suspend fun findById(id: String): FavoritesEntity?
}
