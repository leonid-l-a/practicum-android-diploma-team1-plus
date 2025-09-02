package ru.practicum.android.diploma.core.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import ru.practicum.android.diploma.BuildConfig

class APIKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val curRequest = chain.request()
        return chain.proceed(
            curRequest.newBuilder()
                .header("Authorization", "Bearer ${BuildConfig.API_ACCESS_TOKEN}")
                .build()
        )
    }
}
