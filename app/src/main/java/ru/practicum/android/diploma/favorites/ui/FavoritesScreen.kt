package ru.practicum.android.diploma.favorites.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.components.CircularIndicator
import ru.practicum.android.diploma.favorites.ui.components.ShowFavoritesList
import ru.practicum.android.diploma.favorites.ui.components.ShowPlaceHolder
import ru.practicum.android.diploma.favorites.ui.state.FavoriteState
import ru.practicum.android.diploma.favorites.ui.viewmodel.FavoritesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = koinViewModel(),
    onVacancyClick: (String) -> Unit = {}
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
                        text = stringResource(R.string.favorite_title),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShowContent(viewModel = viewModel, onVacancyClick = onVacancyClick)
        }
    }
}

@Composable
fun ShowContent(
    viewModel: FavoritesViewModel,
    onVacancyClick: (String) -> Unit = {}
) {
    val favoritesState by viewModel.stateFavoritesVacancy.collectAsState()
    when (favoritesState) {
        is FavoriteState.Empty ->
            ShowPlaceHolder(R.drawable.favorites_placeholder, R.string.favorites_is_empty)

        is FavoriteState.Error ->
            ShowPlaceHolder(R.drawable.favorites_error, R.string.favorites_is_error)

        is FavoriteState.Loading -> CircularIndicator()

        is FavoriteState.Content -> {
            ShowFavoritesList(
                (favoritesState as FavoriteState.Content).vacancy,
                onClick = onVacancyClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FavoriteScreenPreview() {
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
                        text = stringResource(R.string.favorite_title),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShowPlaceHolder(R.drawable.favorites_placeholder, R.string.favorites_is_empty)
        }
    }
}
