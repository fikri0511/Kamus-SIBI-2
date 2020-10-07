package com.dicoding.core.data


import com.dicoding.core.data.remote.network.ApiResponse
import kotlinx.coroutines.flow.*


abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result: Flow<Resource<ResultType>> =
        flow {
            emit(Resource.Loading())
            val dbSource = loadFromDB().first()
            if (shouldFetch(dbSource)) {
                emit(Resource.Loading())
                when (val api = createCall().first()) {
                    //jika api sukses
                    is ApiResponse.Success -> {
                        saveCallResult(api.data)
                        emitAll(loadFromDB().map {
                            Resource.Success(
                                it
                            )
                        })
                    }
                    //jika api kosong
                    is ApiResponse.Empty -> {
                        emitAll(loadFromDB().map {
                            Resource.Success(
                                it
                            )
                        })
                    }
                    //jika api error/gagal
                    is ApiResponse.Error -> {
                        onFetchFailed()
                        emit(Resource.Error(api.errorMessage))
                    }
                }
            } else {
                emitAll(loadFromDB().map {
                    Resource.Success(
                        it
                    )
                })
            }
        }


    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}