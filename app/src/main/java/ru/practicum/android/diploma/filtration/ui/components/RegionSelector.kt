package ru.practicum.android.diploma.filtration.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.components.FilterItem
import ru.practicum.android.diploma.filtration.domain.model.Region

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegionSelector(
    regions: List<Region>,
    /** viewModel: AreasViewModel, */
    onClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults
                    .topAppBarColors()
                    .copy(
                        containerColor = Color.Transparent
                    ),
                title = {
                    Text(
                        text = stringResource(R.string.region_selector),
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            // TODO Изменение стейта с прокидыванием значения (типа навигация наша)
                        }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues = paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = regions) {
                FilterItem(
                    labelText = it.name,
                    checked = false,
                    onClick = onClick
                ) { checked ->
                    val resId = if (checked) {
                        R.drawable.close_24
                    } else {
                        R.drawable.arrow_forward_24
                    }
                    Icon(
                        tint = MaterialTheme.colorScheme.onBackground,
                        painter = painterResource(
                            id = resId
                        ),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegionSelectorPreview() {
    val regions = listOf(
        Region(1, "Вайтран", 0),
        Region(2, "Врата Балдура", 0),
        Region(3, "Новиград", 0),
        Region(4, "Оргриммар", 0),
        Region(5, "Штормград", 0),
        Region(6, "Лордерон", 0),
        Region(7, "Невервинтер", 0),
        Region(8, "Священная Терра", 0)
    )
    RegionSelector(regions = regions, {})
}
