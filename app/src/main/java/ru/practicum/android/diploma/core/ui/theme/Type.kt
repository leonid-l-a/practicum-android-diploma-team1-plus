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

    headlineLarge = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.Bold,
        fontSize = FontSizeLarge,
        lineHeight = LineHeightLarge,
        letterSpacing = LetterSpacing
    ),

    headlineMedium = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeMedium,
        lineHeight = LineHeightLarge,
        letterSpacing = LetterSpacing
    ),

    headlineSmall = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeSmall,
        lineHeight = LineHeightMedium,
        letterSpacing = LetterSpacing
    ),

    bodyLarge = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.W400,
        fontSize = FontSizeSmall,
        lineHeight = LineHeightMedium,
        letterSpacing = LetterSpacing
    ),

    titleLarge = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeMedium,
        lineHeight = LineHeightLarge,
        letterSpacing = LetterSpacing
    ),

    labelLarge = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeSmall,
        lineHeight = LineHeightMedium,
        letterSpacing = LetterSpacing
    ),

    labelSmall = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.W400,
        fontSize = FontSizeExtraSmall,
        lineHeight = LineHeightSmall,
        letterSpacing = LetterSpacing
    )

)
