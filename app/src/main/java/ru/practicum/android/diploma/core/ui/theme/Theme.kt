package ru.practicum.android.diploma.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkTheme = darkColorScheme(

    background = Black_Night,
    onBackground = White_Night,

    primary = Blue,
    onPrimary = White_Universal,

    secondary = Red,
    onSecondary = White_Universal,

    surface = Gray,
    onSurface = White_Universal

)

private val LightTheme = lightColorScheme(

    background = White_Day,
    onBackground = Black_Day,

    primary = Blue,
    onPrimary = White_Universal,

    secondary = Red,
    onSecondary = White_Universal,

    surface = Light_Gray,
    onSurface = Black_Universal

)

@Composable

fun ApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkTheme
    } else {
        LightTheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
