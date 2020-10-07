package com.dicoding.core.domain.repository

import com.dicoding.core.data.Resource
import com.dicoding.core.domain.model.Kamus
import kotlinx.coroutines.flow.Flow

interface IKamusRepository {

    fun getAllKamus(): Flow<Resource<List<Kamus>>>

    fun getFavoriteKamus(): Flow<List<Kamus>>

    fun setFavoriteKamus(kamus: Kamus, state: Boolean)

}
