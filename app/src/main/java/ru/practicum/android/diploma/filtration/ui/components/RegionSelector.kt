package ru.practicum.android.diploma.filtration.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.components.FilterItem
import ru.practicum.android.diploma.core.ui.theme.ApplicationTheme
import ru.practicum.android.diploma.core.ui.theme.WidthForInfoImage328
import ru.practicum.android.diploma.filtration.domain.model.Region

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegionSelector(
    regions: List<Region>,
//    viewModel: AreasViewModel = koinViewModel(),
    onClick: (String) -> Unit,
    onSearchHandler: (String) -> Unit,
    onResetRequest: () -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
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
                            // Изменение стейта с прокидыванием значения (типа навигация наша)
                        }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchFilterItems(
                text = text,
                modifier = Modifier,
                onSearchHandler = {
                    onSearchHandler
                    // popBackStack
                },
                onResetRequest = {}
            )

            ShowContent(
//                viewModel = viewModel,
                onClick = onClick,
                onSearchHandler = onSearchHandler,
                onResetRequest = onResetRequest
            )
        }
    }
}

@Composable
fun ShowContent(
//    viewModel: AreasViewModel = koinViewModel(),
    onClick: (String) -> Unit,
    onSearchHandler: (String) -> Unit,
    onResetRequest: () -> Unit = {}
) {
//    val regionState = viewModel.regionsMap.collectAsState()

//    when (regionState) {
    // Ну тут уж когда сами стейты будут
//    }
}

@Composable
fun ShowNoRegion() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.width(WidthForInfoImage328),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.no_region),
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.no_region),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview
@Composable
fun ShowNoRegionPreview() {
    ApplicationTheme {
        ShowNoRegion()
    }
}

@Composable
fun ShowErrorRegion() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.width(WidthForInfoImage328),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.region_error),
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.region_error),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview
@Composable
fun ShowErrorRegionPreview() {
    ApplicationTheme {
        ShowErrorRegion()
    }
}

@Composable
fun ShowRegions(
    regions: List<Region>,
    onClick: (String?) -> Unit
) {
    LazyColumn(
        modifier = Modifier,
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegionSelectorPreview() {
    @Suppress("MagicNumber")
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

    ApplicationTheme {
        RegionSelector(
            regions = regions,
//            viewModel =  koinViewModel(),
            onClick = {},
            onSearchHandler = {},
            onResetRequest = {}
        )
    }
}
