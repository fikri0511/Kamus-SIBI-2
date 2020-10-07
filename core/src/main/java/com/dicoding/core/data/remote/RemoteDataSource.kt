package com.dicoding.core.data.remote

import android.util.Log
import com.dicoding.core.data.remote.network.ApiResponse
import com.dicoding.core.data.remote.network.ApiService
import com.dicoding.core.data.remote.response.KamusResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception


class RemoteDataSource constructor(private val apiService: ApiService) {

    fun getAllKamus(): Flow<ApiResponse<List<KamusResponse>>> {
        return flow {
            try {
                val respons = apiService.getList()
                val data = respons.kata
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(respons.kata))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

