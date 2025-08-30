package ru.practicum.android.diploma.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkTheme = darkColorScheme(

    background = AppColor.blackNight,
    onBackground = AppColor.whiteNight,

    primary = AppColor.blue,
    onPrimary = AppColor.whiteUniversal,

    secondary = AppColor.red,
    onSecondary = AppColor.whiteUniversal,

    surface = AppColor.gray,
    onSurface = AppColor.whiteUniversal

)

private val LightTheme = lightColorScheme(

    background = AppColor.whiteDay,
    onBackground = AppColor.blackDay,

    primary = AppColor.blue,
    onPrimary = AppColor.whiteUniversal,

    secondary = AppColor.red,
    onSecondary = AppColor.whiteUniversal,

    surface = AppColor.lightGray,
    onSurface = AppColor.blackUniversal

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
