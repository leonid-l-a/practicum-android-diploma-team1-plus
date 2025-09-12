package ru.practicum.android.diploma.command.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.theme.Height24
import ru.practicum.android.diploma.core.ui.theme.Height96
import ru.practicum.android.diploma.core.ui.theme.SpacerHeight24
import ru.practicum.android.diploma.core.ui.theme.SpacerHeight8
import ru.practicum.android.diploma.core.ui.theme.WrapperPaddingHorizontal16

@Composable
fun ApplicationDevelopers(
    modifier: Modifier,
    innerPadding: PaddingValues,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = WrapperPaddingHorizontal16),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(Height96),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(R.string.app_developers),
                style = MaterialTheme.typography.headlineLarge,
                maxLines = 2
            )
        }

        Spacer(modifier = modifier.height(SpacerHeight24))

        Developers(
            modifier = modifier,
        )
    }
}

@Composable
fun Developers(
    modifier: Modifier,
) {
    val developers = stringResource(R.string.developers)
        .split(",")
        .map { it.trim()
        }

    Column(
        verticalArrangement = Arrangement.spacedBy(SpacerHeight8)
    ) {
        developers.forEach { develop ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(Height24),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = develop,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}
