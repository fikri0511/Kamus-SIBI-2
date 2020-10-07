package com.dicoding.core.di

import androidx.room.Room
import com.dicoding.core.data.KamusRepository
import com.dicoding.core.data.local.LocalDataSource
import com.dicoding.core.data.local.room.KamusDatabase
import com.dicoding.core.data.remote.RemoteDataSource
import com.dicoding.core.data.remote.network.ApiService
import com.dicoding.core.domain.repository.IKamusRepository
import com.dicoding.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<KamusDatabase>().kamusDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            KamusDatabase::class.java, "Kamus.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://firebasestorage.googleapis.com/v0/b/kamus-2b441.appspot.com/o/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IKamusRepository> {
        KamusRepository(
            get(),
            get(),
            get()
        )
    }
}