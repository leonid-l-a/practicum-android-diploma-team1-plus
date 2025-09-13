package ru.practicum.android.diploma.filtration.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.core.ui.theme.ApplicationTheme
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.theme.Height24
import ru.practicum.android.diploma.core.ui.theme.Height56
import ru.practicum.android.diploma.core.ui.theme.blackUniversal
import ru.practicum.android.diploma.core.ui.theme.blue

@Composable
fun SalaryField(
    value: String,
    onValueChange: (String) -> Unit,
    topPlaceholder: @Composable ((Color) -> Unit),
    bottomPlaceholder: @Composable ((Color) -> Unit),
    modifier: Modifier = Modifier,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    var isFocused by remember { mutableStateOf(false) }

    BasicTextField(
        modifier = modifier
            .height(height = Height56)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium
            )
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        value = value,
        textStyle = LocalTextStyle.current.copy(
            color = blackUniversal
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        cursorBrush = SolidColor(value = blue),
        onValueChange = onValueChange,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1F)
                        .fillMaxHeight()
                ) {
                    val topLabelColor = if (isFocused) {
                        blue
                    } else if (value.isNotEmpty()) {
                        blackUniversal
                    } else {
                        MaterialTheme.colorScheme.onTertiary
                    }
                    topPlaceholder(topLabelColor)
                    Box(Modifier.fillMaxWidth()) {
                        if (value.isEmpty()) {
                            val bottomLabelColor = if (value.isNotEmpty() && !isFocused) {
                                blackUniversal
                            } else {
                                MaterialTheme.colorScheme.onTertiary
                            }
                            bottomPlaceholder(bottomLabelColor)

                        }
                        innerTextField()
                    }
                }
                if (value.isNotEmpty() && isFocused) {
                    trailingIcon?.invoke()
                }
            }
        }
    )
}

@Preview(
    showBackground = true, showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun SalaryFieldPreview() {
    ApplicationTheme {
        var text by remember {
            mutableStateOf("")
        }
        Column {
            SalaryField(
                value = text,
                onValueChange = { newText ->
                    text = newText.filter { it.isDigit() }
                },
                topPlaceholder = { topLabelColor ->
                    Text(
                        text = stringResource(R.string.need_salary),
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.W400),
                        color = topLabelColor,
                    )
                },
                bottomPlaceholder = { bottomLabelColor ->
                    Text(
                        text = stringResource(R.string.input_salary),
                        color = bottomLabelColor,
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(Height24)
                            .clickable {
                                text = ""
                            },
                        painter = painterResource(R.drawable.close_24),
                        contentDescription = null
                    )
                }
            )
        }
    }
}
