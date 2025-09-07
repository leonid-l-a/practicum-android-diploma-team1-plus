package ru.practicum.android.diploma.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.theme.dividerColor

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val navigationColors = NavigationBarItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.primary,
        selectedTextColor = MaterialTheme.colorScheme.primary,
        indicatorColor = Color.Transparent,
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
    )

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(dividerColor)
        )

        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ) {
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = R.drawable.ic_main),
                        contentDescription = "Main"
                    )
                },
                label = {
                    Text(
                        "Главная",
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                selected = currentRoute == Screen.Main.route,
                onClick = {
                    if (currentRoute != Screen.Main.route) {
                        navController.navigate(Screen.Main.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = navigationColors,
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = R.drawable.ic_favorites),
                        contentDescription = "Favourite"
                    )
                },
                label = {
                    Text(
                        "Избранное",
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                selected = currentRoute == Screen.Favourites.route,
                onClick = {
                    if (currentRoute != Screen.Favourites.route) {
                        navController.navigate(Screen.Favourites.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = navigationColors
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = R.drawable.ic_command),
                        contentDescription = "Command"
                    )
                },
                label = {
                    Text(
                        "Команда",
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                selected = currentRoute == Screen.Command.route,
                onClick = {
                    if (currentRoute != Screen.Command.route) {
                        navController.navigate(Screen.Command.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = navigationColors
            )
        }
    }
}
