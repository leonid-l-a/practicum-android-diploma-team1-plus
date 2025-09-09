package ru.practicum.android.diploma.core.ui.root

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.practicum.android.diploma.core.navigation.AppNavHost
import ru.practicum.android.diploma.core.navigation.BottomNavigationBar
import ru.practicum.android.diploma.core.navigation.Screen
import ru.practicum.android.diploma.core.ui.theme.ApplicationTheme

class RootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            val bottomBarRoutes = listOf(
                Screen.Main.route,
                Screen.Favorites.route,
                Screen.Command.route
            )
            ApplicationTheme {
                Scaffold(
                    bottomBar = {
                        if (currentRoute in bottomBarRoutes) {
                            BottomNavigationBar(navController)
                        }
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
