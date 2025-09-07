package ru.practicum.android.diploma.util

import android.util.Log
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.toRoute
import ru.practicum.android.diploma.core.navigation.TestScreen

inline fun <reified T : TestScreen> NavBackStackEntry?.toRouteOrNull(): T? {
    return try {
        val t = this?.toRoute<T>()
        Log.d("CHECK_TITLE", "toRouteOrNull not null")
        return t
    } catch (e: Exception) {
        Log.d("CHECK_TITLE", "toRouteOrNull null")
        null
    }
}
