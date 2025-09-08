package ru.practicum.android.diploma.core.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import ru.practicum.android.diploma.vacancy.ui.components.VacancyScreen
import ru.practicum.android.diploma.vacancy.ui.viewmodel.VacancyViewModel

/**
 * The application's navigation host, managing navigation between screens.
 *
 * @param navController The navigation controller used to handle screen transitions.
 */
@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Screen.Main.route, modifier = modifier) {
        // Заменить заглушки на реальные экраны

        composable(Screen.Main.route) { Text("Main Screen") }

        composable(Screen.Command.route) { Text("Command Screen") }
        composable(Screen.Favourites.route) { Text("Favourite Screen") }
        composable(
            route = Screen.VacancyDetails.route,
            arguments = listOf(navArgument("vacancyId") { type = NavType.IntType })
        ) { backStackEntry ->
            val viewModel: VacancyViewModel = koinViewModel()
            VacancyScreen(viewModel = viewModel,)
        }
        composable(Screen.Filtration.route) { Text("Filtration Screen") }
        composable(Screen.Placement.route) { Text("Placement Screen") }
        composable(Screen.CountrySelection.route) { Text("Country Selection Screen") }
        composable(Screen.RegionSelection.route) { Text("Region Selection Screen") }
        composable(Screen.IndustrySelection.route) { Text("Industry Selection Screen") }
    }
}
