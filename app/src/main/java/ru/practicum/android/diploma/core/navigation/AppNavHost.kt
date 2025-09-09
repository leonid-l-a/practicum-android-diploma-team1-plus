package ru.practicum.android.diploma.core.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.command.CommandScreen
import ru.practicum.android.diploma.main.ui.SearchScreen
import ru.practicum.android.diploma.main.ui.viewmodel.SearchVacancyViewModel

/**
 * The application's navigation host, managing navigation between screens.
 *
 * @param navController The navigation controller used to handle screen transitions.
 */
@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Screen.Main.route, modifier = modifier) {
        // Заменить заглушки на реальные экраны

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
        composable(Screen.Favorites.route) { Text("Favourite Screen") }
        composable(Screen.VacancyDetails.route) { Text("Vacancy Details Screen") }
        composable(Screen.Filtration.route) { Text("Filtration Screen") }
        composable(Screen.Placement.route) { Text("Placement Screen") }
        composable(Screen.CountrySelection.route) { Text("Country Selection Screen") }
        composable(Screen.RegionSelection.route) { Text("Region Selection Screen") }
        composable(Screen.IndustrySelection.route) { Text("Industry Selection Screen") }
    }
}
