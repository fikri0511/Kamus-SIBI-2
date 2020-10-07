package com.dicoding.core.data.local

import com.dicoding.core.data.local.entity.KamusEntity
import com.dicoding.core.data.local.room.KamusDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource  constructor(private val kamusDao: KamusDao) {

    fun getAllKamus(): Flow<List<KamusEntity>> = kamusDao.getAllKamus()

    fun getFavoriteKamus(): Flow<List<KamusEntity>> = kamusDao.getFavoriteKamus()

    suspend fun insertKamus(kamusList: List<KamusEntity>) = kamusDao.insertKamus(kamusList)

    fun setFavoriteKamus(kamus: KamusEntity, newState: Boolean) {
        kamus.isFavorite = newState
        kamusDao.updateFavoriteKamus(kamus)
    }
}