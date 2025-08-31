package ru.practicum.android.diploma.core.ui.root

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ru.practicum.android.diploma.core.navigation.AppNavHost
import ru.practicum.android.diploma.core.navigation.BottomNavigationBar
import ru.practicum.android.diploma.core.ui.theme.ApplicationTheme

class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ApplicationTheme {
                Scaffold(bottomBar = {
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
