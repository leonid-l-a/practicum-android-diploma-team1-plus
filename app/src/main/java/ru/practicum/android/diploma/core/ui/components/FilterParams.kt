package ru.practicum.android.diploma.core.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import ru.practicum.android.diploma.core.ui.theme.gray

@Immutable
object FilterParams {
    const val ONE_LINE = 1
    const val TWO_LINE = 2

    enum class FIELDTYPE {
        TEXT,
        CHECK_BOX,
        RADIO_BUTTON
    }

    @Composable
    internal fun getParams(checked: Boolean): Triple<TextStyle, Color, Int> {
        val typography = if (checked) {
            MaterialTheme.typography.labelSmall
        } else {
            MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W400)
        }
        val color = if (checked) {
            MaterialTheme.colorScheme.onBackground
        } else {
            gray
        }
        val maxLines = if (checked) ONE_LINE else TWO_LINE
        return Triple(
            first = typography,
            second = color,
            third = maxLines
        )
    }
}
