package ru.practicum.android.diploma.main.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.core.ui.theme.CircleSize36
import ru.practicum.android.diploma.core.ui.theme.blue

@Composable
fun LoadingRow(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .height(80.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(CircleSize36),
            color = blue,
            trackColor = Color.Transparent,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoadingRowPreview() {
    LoadingRow()
}
