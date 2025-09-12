package ru.practicum.android.diploma.main.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.theme.TopAppBarHeight64

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VacancyAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier.height(TopAppBarHeight64),
        colors = TopAppBarDefaults
            .topAppBarColors()
            .copy(
                containerColor = Color.Transparent
            ),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = stringResource(R.string.search_title)
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_filter),
                    contentDescription = null
                )
            }
        },
    )
}
