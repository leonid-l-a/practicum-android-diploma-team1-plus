package ru.practicum.android.diploma.core.navigation

import androidx.annotation.StringRes
import kotlinx.serialization.Serializable
import ru.practicum.android.diploma.R

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
    object Favourites : Screen("favourite")
    object Filtration : Screen("filtration")
    object Placement : Screen("placement")
    object CountrySelection : Screen("country_selection")
    object RegionSelection : Screen("region_selection")
    object IndustrySelection : Screen("industry_selection")
}

/*enum class Routes(@StringRes val title: Int) {
    Main(title = R.string.search_title),
    VacancyDetails(title = R.string.search_title),
    Command(title = R.string.search_title),
    Favourites(title = R.string.search_title),
    Filtration(title = R.string.search_title),
    Placement(title = R.string.search_title),
    CountrySelection(title = R.string.search_title),
    RegionSelection(title = R.string.search_title),
    IndustrySelection(title = R.string.search_title)
}*/
data class TopLevelRoute<T : Any>(val name: String, val route: T)

@Serializable
sealed interface TestScreen {
    companion object {
        fun fromRoute(route: String): Screen? {
            return Screen::class.sealedSubclasses.firstOrNull {
                route.contains(it.qualifiedName.toString())
            }?.objectInstance
        }
    }


    val title: Int

    @Serializable
    data class Main(
        @StringRes override val title: Int,
    ): TestScreen

    @Serializable
    data class VacancyDetails(
        val data: String,
        @StringRes override val title: Int,
    ): TestScreen

    @Serializable
    data class Command(
        @StringRes override val title: Int,
    ): TestScreen

    @Serializable
    data class Favourites(
        @StringRes override val title: Int,
    ): TestScreen

    @Serializable
    data class Filtration(
        @StringRes override val title: Int,
    ): TestScreen

    @Serializable
    data class Placement(
        @StringRes override val title: Int,
    ): TestScreen

    @Serializable
    data class CountrySelection(
        @StringRes override val title: Int,
    ): TestScreen

    @Serializable
    data class RegionSelection(
        @StringRes override val title: Int,
    ): TestScreen

    @Serializable
    data class IndustrySelection(
        @StringRes override val title: Int,
    ): TestScreen
}

val bottomNavigationRoute = listOf(
    TopLevelRoute(
        name = "Main",
        route = TestScreen.Main(
            title = R.string.search_title
        )
    ),
    TopLevelRoute(
        name = "Favourite",
        route = TestScreen.Favourites(
            title = R.string.favorite_title
        )
    ),
    TopLevelRoute(
        name = "Command",
        route = TestScreen.Command(
            title = R.string.command_title
        )
    )
)
