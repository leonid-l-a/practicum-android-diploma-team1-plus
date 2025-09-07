package ru.practicum.android.diploma.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import ru.practicum.android.diploma.core.navigation.Screen
import ru.practicum.android.diploma.core.navigation.TestScreen
import ru.practicum.android.diploma.core.ui.theme.TopAppBarHeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VacancyAppBar(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    screen: TestScreen,
    canNavigateUp: Boolean
) {
    TopAppBar(
        modifier = modifier.height(TopAppBarHeight),
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
                    text = stringResource(screen.title)
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_filter),
                    contentDescription = null
                )

                /*when(screen) {
                    is TestScreen.Command -> TODO()
                    is TestScreen.CountrySelection -> TODO()
                    is TestScreen.Favourites -> TODO()
                    is TestScreen.Filtration -> TODO()
                    is TestScreen.IndustrySelection -> TODO()
                    is TestScreen.Main -> TODO()
                    is TestScreen.Placement -> TODO()
                    is TestScreen.RegionSelection -> TODO()
                    is TestScreen.VacancyDetails -> TODO()
                }*/
            }
        },
        navigationIcon = {
            if (canNavigateUp) {
                //TODO BACK BUTTON
                IconButton(onClick = navigateUp) {
                    Text("Жмакаю назад")
                }
            }
        }
    )
}
