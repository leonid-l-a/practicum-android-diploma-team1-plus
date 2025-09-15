package ru.practicum.android.diploma.filtration.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkPlaceSelector(
    onCountryClick: (String) -> Unit,
    onRegionClick: (String) -> Unit,
    /** viewModel: AreasViewModel,
     *  navController: NavController */
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
                        text = stringResource(R.string.work_place_title),
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            // navController.popBackStack()
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
                .padding(paddingValues = paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilterItem(
                labelText = stringResource(R.string.country),
                checked = false,
                isMainField = true,
                onClick = onCountryClick,
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
            FilterItem(
                labelText = stringResource(R.string.region),
                checked = false,
                isMainField = true,
                onClick = onRegionClick,
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
fun WorkPlaceScreenPreview() {
    WorkPlaceSelector({}, {})
}
