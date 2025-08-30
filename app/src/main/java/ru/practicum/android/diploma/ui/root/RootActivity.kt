package ru.practicum.android.diploma.ui.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.R

class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        // Пример использования access token для HeadHunter API
        networkRequestExample(accessToken = BuildConfig.API_ACCESS_TOKEN)
    }

    private fun networkRequestExample(accessToken: String) {
        // ...
    }

}
