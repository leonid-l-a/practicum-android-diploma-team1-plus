package ru.practicum.android.diploma.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.core.ui.theme.HeightMainSearchBar
import ru.practicum.android.diploma.main.ui.components.SearchBar

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    Column {
        SearchBar(
            modifier = modifier,
            height = HeightMainSearchBar
        )
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}
