package com.dicoding.core.data.remote.network

import com.dicoding.core.data.remote.response.ListKamusResponse
import retrofit2.http.GET

interface ApiService {
    @GET("kamus.json?alt=media&token=f765b9c7-351a-4574-beaa-7bd243d5726a")
    suspend fun getList(): ListKamusResponse
}