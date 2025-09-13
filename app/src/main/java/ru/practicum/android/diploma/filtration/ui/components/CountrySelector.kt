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
import ru.practicum.android.diploma.filtration.domain.model.Country

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountrySelector(
    countries: List<Country>,
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
                        text = stringResource(R.string.country_selector),
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
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues = paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(countries) {
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
fun CountryScreenPreview() {
    @Suppress("MagicNumber")
    val countries = listOf(
        Country(1, "Скайрим"),
        Country(2, "Морровинд"),
        Country(3, "Побережье мечей"),
        Country(4, "Альянс"),
        Country(5, "Орда"),
        Country(6, "Империум человечества"),
        Country(7, "Вестерос"),
        Country(8, "Эссос")
    )
    CountrySelector(countries = countries, {})
}
