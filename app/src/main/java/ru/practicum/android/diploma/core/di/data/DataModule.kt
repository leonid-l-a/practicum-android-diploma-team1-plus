package ru.practicum.android.diploma.core.di.data

import android.content.Context
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.core.data.network.VacancyApiService
import ru.practicum.android.diploma.core.data.network.VacancyNetworkClient
import ru.practicum.android.diploma.core.data.network.interceptors.APIKeyInterceptor
import ru.practicum.android.diploma.core.data.sharedprefs.AppStorage

private const val APP_STORAGE = "app_storage"

val coreDataModule = module {
    single<APIKeyInterceptor> { APIKeyInterceptor() }
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(interceptor = get<APIKeyInterceptor>())
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

    single {
        androidContext()
            .getSharedPreferences(APP_STORAGE, Context.MODE_PRIVATE)
    }

    single<AppStorage> {
        AppStorage(get())
    }

}
