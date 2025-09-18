package ru.practicum.android.diploma.filtration.ui.screens.preview

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.core.ui.theme.ApplicationTheme
import ru.practicum.android.diploma.filtration.ui.screens.IndustryFilterScreen

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun IndustryFilterScreenPreview() {
    ApplicationTheme {
        IndustryFilterScreen()
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun IndustryFilterScreenNightPreview() {
    ApplicationTheme {
        IndustryFilterScreen()
    }
}
