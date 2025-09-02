package ru.practicum.android.diploma.core.ui.root

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.android.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.core.data.dto.VacancyRequest
import ru.practicum.android.diploma.core.data.network.VacancyApiService
import ru.practicum.android.diploma.core.data.network.VacancyNetworkClient
import ru.practicum.android.diploma.core.navigation.AppNavHost
import ru.practicum.android.diploma.core.navigation.BottomNavigationBar
import ru.practicum.android.diploma.core.ui.theme.ApplicationTheme

class RootActivity : AppCompatActivity() {
    val client: VacancyNetworkClient by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ApplicationTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ) { padding ->
                    AppNavHost(
                        navController,
                        modifier = Modifier.padding(padding)
                    )
                }
            }

        }
    }
}
