package com.dicoding.core.data

import com.dicoding.core.data.local.LocalDataSource
import com.dicoding.core.data.remote.RemoteDataSource
import com.dicoding.core.data.remote.network.ApiResponse
import com.dicoding.core.data.remote.response.KamusResponse
import com.dicoding.core.domain.model.Kamus
import com.dicoding.core.domain.repository.IKamusRepository
import com.dicoding.core.utils.AppExecutors
import com.dicoding.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class KamusRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IKamusRepository {

    override fun getAllKamus(): Flow<Resource<List<Kamus>>> =
        object : NetworkBoundResource<List<Kamus>, List<KamusResponse>>() {
            override fun loadFromDB(): Flow<List<Kamus>> {
                return localDataSource.getAllKamus().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Kamus>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<KamusResponse>>> =
                remoteDataSource.getAllKamus()

            override suspend fun saveCallResult(data: List<KamusResponse>) {
                val kamusList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertKamus(kamusList)
            }
        }.asFlow()

    override fun getFavoriteKamus(): Flow<List<Kamus>> {
        return localDataSource.getFavoriteKamus().map { DataMapper.mapEntitiesToDomain(it) }

    }

    override fun setFavoriteKamus(kamus: Kamus, state: Boolean) {
        val kamusEntity = DataMapper.mapDomainToEntity(kamus)
        appExecutors.diskIO().execute { localDataSource.setFavoriteKamus(kamusEntity, state) }
    }
}

