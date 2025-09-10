package ru.practicum.android.diploma.vacancy.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.vacancy.ui.viewmodel.VacancyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(viewModel: VacancyViewModel, navController: NavController) {
    val context = LocalContext.current
    TopAppBar(
        title = {
            Text(
                "Вакансия",
                style = MaterialTheme.typography.headlineMedium,
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        actions = {
            Row {
                IconButton(onClick = {
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_favorites_off),
                        contentDescription = "Share",
                    )
                }

                IconButton(onClick = {
                    viewModel.shareVacancyWithMessenger()?.let { intent ->
                        context.startActivity(intent)
                    }
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_share),
                        contentDescription = "Share",
                    )
                }
            }
        }
    )
}
