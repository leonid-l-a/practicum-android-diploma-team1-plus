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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldCustom(
    value: String,
    contentPadding: PaddingValues,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = TextFieldDefaults.shape,
    colors: TextFieldColors = TextFieldDefaults.colors(),

    ) {
    @Suppress("NAME_SHADOWING")
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    // If color is not provided via the text style, use content color as a default
    val textColor =
        textStyle.color.takeOrElse {
            val focused = interactionSource.collectIsFocusedAsState().value
            //colors.textColor(enabled, isError, focused)
            when {
                !enabled -> colors.disabledTextColor
                isError -> colors.errorTextColor
                focused -> colors.focusedTextColor
                else -> colors.unfocusedTextColor
            }
        }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    CompositionLocalProvider(LocalTextSelectionColors provides colors.textSelectionColors) {
        BasicTextField(
            value = value,
            modifier =
                modifier
                    .defaultMinSize(
                        minWidth = TextFieldDefaults.MinWidth,
                    ),
            onValueChange = onValueChange,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = mergedTextStyle,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            interactionSource = interactionSource,
            cursorBrush = SolidColor(if (isError) colors.errorCursorColor else colors.cursorColor),
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            decorationBox =
                @Composable { innerTextField ->
                    // places leading icon, text field with label and placeholder, trailing icon
                    TextFieldDefaults.DecorationBox(
                        contentPadding = contentPadding,
                        value = value,
                        visualTransformation = visualTransformation,
                        innerTextField = innerTextField,
                        placeholder = placeholder,
                        label = label,
                        leadingIcon = leadingIcon,
                        trailingIcon = trailingIcon,
                        prefix = prefix,
                        suffix = suffix,
                        supportingText = supportingText,
                        shape = shape,
                        singleLine = singleLine,
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = colors
                    )
                }
        )
    }
}


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


@Composable
fun SearchBarCustom(modifier: Modifier = Modifier) {
    var searchText by remember { mutableStateOf("") }

    TextFieldCustom(
        contentPadding = PaddingValues(8.dp),
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.W400,
            fontFamily = FontFamily(
                Font(
                    resId = R.font.ys_display_regular,
                    weight = FontWeight.W400
                )
            )
        ),
        leadingIcon = {
            Icon(
                tint = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.size(16.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                contentDescription = null
            )
        },
        trailingIcon = {
            if (!searchText.isEmpty()) {
                Icon(
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.clickable {
                        //focusManager.clearFocus()
                        //keyboardController?.hide()
                        searchText = ""
                        //onResetHandler()
                    },
                    imageVector = ImageVector.vectorResource(R.drawable.ic_close),
                    contentDescription = null
                )
            }
        },
        placeholder = {
            Text(
                color = MaterialTheme.colorScheme.onSecondary,
                text = "Поиск",
                style = TextStyle(
                    fontWeight = FontWeight(400),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.ys_display_regular))
                ),
            )
        },
        value = searchText,
        onValueChange = {
            searchText = it
            //onSearchHandler(searchText)
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            //.height(36.dp)
            .onFocusChanged { focusState ->
                if (focusState.hasFocus && searchText.isEmpty()) {
                    //onResetHandler()
                }
            },
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.secondary,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
            errorContainerColor = MaterialTheme.colorScheme.secondary,

            //focusedTextColor = yp_black,
            //unfocusedTextColor = yp_black,
            //disabledTextColor = yp_black,
            //errorTextColor = yp_black,

            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,

            //cursorColor = yp_blue
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


