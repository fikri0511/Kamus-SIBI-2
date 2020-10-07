package com.dicoding.core.data.local.room

import androidx.room.*
import com.dicoding.core.data.local.entity.KamusEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface KamusDao {

    @Query("SELECT * FROM kamus")
    fun getAllKamus(): Flow<List<KamusEntity>>

    @Query("SELECT * FROM kamus where isFavorite = 1")
    fun getFavoriteKamus(): Flow<List<KamusEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKamus(kamus: List<KamusEntity>)

    @Update
    fun updateFavoriteKamus(kamus: KamusEntity)
}
