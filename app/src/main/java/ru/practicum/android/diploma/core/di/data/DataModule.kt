package ru.practicum.android.diploma.core.di.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.core.data.network.VacancyApiService
import ru.practicum.android.diploma.core.data.network.VacancyNetworkClient
import ru.practicum.android.diploma.core.data.network.interceptors.APIKeyInterceptor

val coreDataModule = module {
    single<APIKeyInterceptor> { APIKeyInterceptor() }
    single<HttpLoggingInterceptor> { HttpLoggingInterceptor().apply { level =  HttpLoggingInterceptor.Level.HEADERS } }
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(interceptor = get<APIKeyInterceptor>())
            .addInterceptor(interceptor = get<HttpLoggingInterceptor>())
            .build()
    }
    single<VacancyApiService> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(VacancyApiService::class.java)
    }
    single<VacancyNetworkClient> {
        VacancyNetworkClient(apiService = get())
    }
}
