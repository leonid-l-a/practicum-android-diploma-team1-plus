package ru.practicum.android.diploma.favorites.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ru.practicum.android.diploma.core.ui.theme.WrapperPaddingHorizontal

@Composable
fun ShowPlaceHolder(
    @DrawableRes paint: Int,
    @StringRes string: Int
) {
    Image(
        painter = painterResource(paint),
        contentDescription = null
    )
    Text(
        modifier = Modifier
            .padding(top = WrapperPaddingHorizontal)
            .fillMaxWidth(),
        text = stringResource(string),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineMedium
    )
}
