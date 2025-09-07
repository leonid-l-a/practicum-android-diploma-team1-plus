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
        fontSize = FontSizeLarge32,
        lineHeight = LineHeightLarge26,
        letterSpacing = LetterSpacing0
    ),

    headlineMedium = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeMedium22,
        lineHeight = LineHeightLarge26,
        letterSpacing = LetterSpacing0
    ),

    headlineSmall = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeSmall16,
        lineHeight = LineHeightMedium19,
        letterSpacing = LetterSpacing0
    ),

    bodyLarge = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.W400,
        fontSize = FontSizeSmall16,
        lineHeight = LineHeightMedium19,
        letterSpacing = LetterSpacing0
    ),

    titleLarge = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeMedium22,
        lineHeight = LineHeightLarge26,
        letterSpacing = LetterSpacing0
    ),

    labelLarge = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = FontSizeSmall16,
        lineHeight = LineHeightMedium19,
        letterSpacing = LetterSpacing0
    ),

    labelSmall = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.W400,
        fontSize = FontSizeExtraSmall12,
        lineHeight = LineHeightSmall16,
        letterSpacing = LetterSpacing0
    )

)
