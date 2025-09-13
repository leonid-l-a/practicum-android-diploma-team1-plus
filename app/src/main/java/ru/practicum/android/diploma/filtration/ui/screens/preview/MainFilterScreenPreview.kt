package ru.practicum.android.diploma.filtration.ui.screens.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.core.ui.theme.ApplicationTheme
import ru.practicum.android.diploma.filtration.ui.screens.MainFilterScreen

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MainFilterScreenPreview() {
    ApplicationTheme {
        MainFilterScreen()
    }
}
