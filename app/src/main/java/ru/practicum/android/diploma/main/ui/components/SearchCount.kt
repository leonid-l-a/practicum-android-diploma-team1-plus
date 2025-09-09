package ru.practicum.android.diploma.main.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.core.ui.theme.blue
import ru.practicum.android.diploma.core.ui.theme.whiteUniversal

@Composable
fun SearchCount(
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.height(38.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SuggestionChip(
            modifier = Modifier.padding(top = 4.dp),
            onClick = {},
            shape = MaterialTheme.shapes.medium,
            border = null,
            colors = SuggestionChipDefaults.suggestionChipColors(
                containerColor = blue,
                labelColor = whiteUniversal
            ),
            label = {
                Text(
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.W400
                    ),
                    maxLines = 1,
                    text = text
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SearchCountPreview() {
    SearchCount(text = "Ничего не найдено")
}
