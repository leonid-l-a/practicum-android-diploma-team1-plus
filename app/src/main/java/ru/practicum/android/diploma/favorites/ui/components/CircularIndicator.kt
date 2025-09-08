package ru.practicum.android.diploma.favorites.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.core.ui.theme.CircleSize
import ru.practicum.android.diploma.core.ui.theme.blue

@Composable
fun CircularIndicator(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(CircleSize),
            color = blue,
            trackColor = Color.Transparent,
        )
    }
}

@Preview
@Composable
private fun CircularIndicatorPreview() {
    CircularIndicator()
}
