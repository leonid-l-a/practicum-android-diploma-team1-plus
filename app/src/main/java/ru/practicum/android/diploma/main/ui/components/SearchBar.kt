package ru.practicum.android.diploma.main.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.practicum.android.diploma.core.ui.theme.ApplicationTheme
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.theme.WrapperPaddingHorizontal
import ru.practicum.android.diploma.core.ui.theme.WrapperPaddingVertical
import ru.practicum.android.diploma.core.ui.theme.blackUniversal

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {

    TextField(
        value = value,
        modifier = modifier
            .padding(
                horizontal = WrapperPaddingHorizontal,
                vertical = WrapperPaddingVertical,
            )
            .fillMaxWidth(),
        onValueChange = onValueChange,
        label = label,
        placeholder = placeholder,
        trailingIcon = trailingIcon,
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.colors(
            focusedTextColor = blackUniversal,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,

            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,

        )
    )
}

@Preview(
    showSystemUi = true, showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun SearchBarPreview() {
    ApplicationTheme {
        Column {
            var text by remember { mutableStateOf("") }
            SearchBar(
                value = text,
                onValueChange = { newText ->
                    text = newText
                },
                placeholder = {
                    Text(
                        color = MaterialTheme.colorScheme.onSurface,
                        text = stringResource(R.string.search_bar_placeholder),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                },
                trailingIcon = {
                    val ico = if (text.isEmpty()) R.drawable.ic_search else R.drawable.ic_close
                    Icon(
                        tint = blackUniversal,
                        modifier = Modifier.clickable {
                            if (text.isNotEmpty()) {
                                text = ""
                            }
                        },
                        imageVector = ImageVector.vectorResource(id = ico),
                        contentDescription = null
                    )
                }
            )
        }
    }
}


