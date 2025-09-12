package ru.practicum.android.diploma.command.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.theme.TopAppBarHeight64

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommandBar(
    modifier: Modifier
) {
    TopAppBar(
        modifier = modifier
            .height(TopAppBarHeight64),
        colors = TopAppBarDefaults
            .topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
        title = {
            Row(
                modifier = modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.command_title),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    )
}
