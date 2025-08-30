package ru.practicum.android.diploma.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import ru.practicum.android.diploma.R

val YsDisplay = FontFamily(
    Font(R.font.ys_display_bold, FontWeight.Bold),
    Font(R.font.ys_display_medium, FontWeight.Medium),
    Font(R.font.ys_display_regular, FontWeight.W400),
)

val Typography = Typography(

    // Основные заголовки
    headlineLarge = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.Bold,
        fontSize = FontSizeLarge,
        lineHeight = LineHeightLarge,
        letterSpacing = LetterSpacing
    ),

    // Подзаголовки
    headlineMedium = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeMedium,
        lineHeight = LineHeightLarge,
        letterSpacing = LetterSpacing
    ),

    // Подзаголовки 2-го уровня
    headlineSmall = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeSmall,
        lineHeight = LineHeightMedium,
        letterSpacing = LetterSpacing
    ),

    // Основной текст
    bodyLarge = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.W400,
        fontSize = FontSizeSmall,
        lineHeight = LineHeightMedium,
        letterSpacing = LetterSpacing
    ),

    // Текст в TopBar
    titleLarge = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeMedium,
        lineHeight = LineHeightLarge,
        letterSpacing = LetterSpacing
    ),

    // Кнопки
    labelLarge = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeSmall,
        lineHeight = LineHeightMedium,
        letterSpacing = LetterSpacing
    ),

    //BottomNavigation
    labelSmall = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.W400,
        fontSize = FontSizeExtraSmall,
        lineHeight = LineHeightSmall,
        letterSpacing = LetterSpacing
    )

)
