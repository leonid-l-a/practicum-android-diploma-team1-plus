package ru.practicum.android.diploma.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkTheme = darkColorScheme(

    background = whiteNight,
    onBackground = blackNight,

    primary = blue,
    onPrimary = whiteUniversal,

    secondary = red,
    onSecondary = whiteUniversal,

    surface = gray,
    onSurface = whiteUniversal

)

private val LightTheme = lightColorScheme(

    background = whiteDay,
    onBackground = blackDay,

    primary = blue,
    onPrimary = whiteUniversal,

    secondary = red,
    onSecondary = whiteUniversal,

    surface = lightGray,
    onSurface = blackUniversal

)

@Suppress("detekt.AnnotationOnSeparateLine")
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
