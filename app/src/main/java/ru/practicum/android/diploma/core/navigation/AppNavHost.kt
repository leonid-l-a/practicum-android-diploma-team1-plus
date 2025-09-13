package ru.practicum.android.diploma.core.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.command.CommandScreen
import ru.practicum.android.diploma.favorites.ui.FavoritesScreen
import ru.practicum.android.diploma.favorites.ui.viewmodel.FavoritesViewModel
import ru.practicum.android.diploma.main.ui.SearchScreen
import ru.practicum.android.diploma.main.ui.viewmodel.SearchVacancyViewModel
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
        composable(Screen.Main.route) {
            val vm = koinViewModel<SearchVacancyViewModel>()
            SearchScreen(
                modifier = Modifier,
                viewModel = vm
            ) { vacancyId ->
                if (vm.clickDebounce()) {
                    navController.navigate(Screen.VacancyDetails.route + "/$vacancyId")
                }
            }
        }
        composable(Screen.Command.route) {
            CommandScreen(modifier = Modifier)
        }
        composable(Screen.Favorites.route) {
            val vm = koinViewModel<FavoritesViewModel>()
            FavoritesScreen(
                viewModel = vm
            ) { vacancyId ->
                if (vm.clickDebounce()) {
                    navController.navigate(Screen.VacancyDetails.route + "/$vacancyId")
                }
            }
        }
        composable(
            route = Screen.VacancyDetails.route + "/{vacancyId}",
            arguments = listOf(navArgument("vacancyId") { type = NavType.StringType })
        ) { backStackEntry ->
            val viewModel: VacancyViewModel = koinViewModel()
            VacancyScreen(viewModel = viewModel, navController = navController)
        }
        composable(Screen.Filtration.route) { Text("Filtration Screen") }
        composable(Screen.Placement.route) { Text("Placement Screen") }
        composable(Screen.CountrySelection.route) { Text("Country Selection Screen") }
        composable(Screen.RegionSelection.route) { Text("Region Selection Screen") }
        composable(Screen.IndustrySelection.route) { Text("Industry Selection Screen") }
    }
}
