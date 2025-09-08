package ru.practicum.android.diploma.core.navigation

/**
 * Defines the screens of the application and their routes for navigation.
 *
 * @property route The route of the screen used in navigation.
 *
 * Usage examples:
 *
 * Navigate **to a screen**:
 * ```
 * val navController = rememberNavController()
 * navController.navigate(Screen.Favourite.route)
 * ```
 *
 * Navigate **back to the previous screen**:
 * ```
 * navController.popBackStack()
 * ```
 */

sealed class Screen(val route: String) {
    object Main : Screen("search")
    object VacancyDetails : Screen("vacancy_details")
    object Command : Screen("command")
    object Favorites : Screen("favorite")
    object Filtration : Screen("filtration")
    object Placement : Screen("placement")
    object CountrySelection : Screen("country_selection")
    object RegionSelection : Screen("region_selection")
    object IndustrySelection : Screen("industry_selection")
}
