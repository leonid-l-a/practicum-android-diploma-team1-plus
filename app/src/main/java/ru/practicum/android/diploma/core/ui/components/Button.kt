package ru.practicum.android.diploma.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.theme.ApplicationTheme
import ru.practicum.android.diploma.core.ui.theme.Height30
import ru.practicum.android.diploma.core.ui.theme.Height60
import ru.practicum.android.diploma.core.ui.theme.red

@Composable
fun FilterButton(
    textButton: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.background,
    containerColor: Color = Color.Unspecified,
    onClick: () -> Unit,
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            contentColor = textColor,
            containerColor = containerColor
        ),
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium
    ) {
        Text(text = textButton)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun FilterButtonPreview() {
    ApplicationTheme {
        Column {
            FilterButton(
                modifier = Modifier
                    .height(Height60)
                    .fillMaxWidth(),
                textButton = stringResource(R.string.filter_apply),
                onClick = {}
            )
            Spacer(modifier = Modifier.height(Height30))
            FilterButton(
                modifier = Modifier
                    .height(Height60)
                    .fillMaxWidth(),
                textColor = red,
                containerColor = Color.Transparent,
                textButton = stringResource(R.string.filter_reset),
                onClick = {}
            )
        }
    }
}
