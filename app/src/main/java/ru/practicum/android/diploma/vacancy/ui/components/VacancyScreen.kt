package ru.practicum.android.diploma.vacancy.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.data.dto.vacancydetails.Salary
import ru.practicum.android.diploma.core.ui.theme.lightGray
import ru.practicum.android.diploma.core.ui.theme.whiteNight
import ru.practicum.android.diploma.vacancy.ui.state.VacancyState
import ru.practicum.android.diploma.vacancy.ui.viewmodel.VacancyViewModel

@Composable
fun VacancyScreen(viewModel: VacancyViewModel) {
    val horizontalPadding = 16.dp
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = { TopBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = horizontalPadding)
                .verticalScroll(rememberScrollState())
        ) {
            when (state) {
                is VacancyState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is VacancyState.ServerError -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ph_server_error_vacancy_screen),
                            contentDescription = "Server error",
                        )
                    }
                }

                is VacancyState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ph_error_vacancy_screen),
                            contentDescription = "Server error",
                        )
                    }
                }

                is VacancyState.Success -> {
                    val vacancy = (state as VacancyState.Success).vacancyDetail

                    HorizontalDivider(
                        thickness = 16.dp,
                        color = Color.Transparent
                    )

                    Text(
                        text = vacancy.name,
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Text(
                        text = vacancy.salary.toDisplayString(),
                        style = MaterialTheme.typography.headlineMedium
                    )

                    HorizontalDivider(
                        thickness = 16.dp,
                        color = Color.Transparent
                    )

                    Card(
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = lightGray)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = vacancy.employer.logo,
                                contentDescription = "Loaded image",
                                modifier = Modifier
                                    .size(48.dp),
                                placeholder = painterResource(R.drawable.ic_employer_logo_placeholder),
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Column(
                                modifier = Modifier
                                    .height(48.dp),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = vacancy.employer.name,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = whiteNight
                                )
                                Text(
                                    text = vacancy.address.city,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = whiteNight
                                )
                            }
                        }
                    }

                    HorizontalDivider(
                        thickness = 24.dp,
                        color = Color.Transparent
                    )

                    Text(
                        "Требуемый опыт",
                        style = MaterialTheme.typography.headlineSmall
                    )

                    HorizontalDivider(
                        thickness = 4.dp,
                        color = Color.Transparent
                    )

                    Text(
                        vacancy.experience.name,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    HorizontalDivider(
                        thickness = 8.dp,
                        color = Color.Transparent
                    )

                    Text(
                        vacancy.employment.name + ", " + "Удалённая работа",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    HorizontalDivider(
                        thickness = 32.dp,
                        color = Color.Transparent
                    )

                    Text("Описание вакансии", style = MaterialTheme.typography.headlineMedium)

                    HorizontalDivider(
                        thickness = 16.dp,
                        color = Color.Transparent
                    )

                    val otherItems = listOf(
                        "График" to listOfNotNull(vacancy.schedule.name),
                        "Контакты" to listOfNotNull(
                            vacancy.contacts.email.takeIf { it.isNotBlank() },
                            vacancy.contacts.phone?.joinToString(),
                            vacancy.contacts.name
                        ),
                        "Навыки" to vacancy.skills,
                        "Отрасль" to listOfNotNull(vacancy.industry.name),
                        "Адрес" to listOfNotNull(
                            listOfNotNull(
                                vacancy.address.city,
                                vacancy.address.street,
                                vacancy.address.building
                            ).takeIf { it.isNotEmpty() }?.joinToString(", ")
                        ),
                        "Описание" to vacancy.description
                            .split(Regex("<br>|<br/>|<br />"))
                            .map { it.trim() }
                            .filter { it.isNotEmpty() },
                        )

                    for ((title, desc) in otherItems) {
                        if (desc.isNotEmpty()) {
                            VacancyDescriptionItem(
                                title = title,
                                description = desc
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                "Вакансия",
                style = MaterialTheme.typography.headlineMedium,
            )
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        )

    )

}

@Composable
fun VacancyDescriptionItem(title: String, description: List<String>) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
        )

        HorizontalDivider(
            thickness = 4.dp,
            color = Color.Transparent
        )

        for (item in description) {
            Text(
                text = " • $item",
                style = MaterialTheme.typography.bodyLarge,
            )
        }

        HorizontalDivider(
            thickness = 12.dp,
            color = Color.Transparent
        )
    }
}

fun Salary.toDisplayString(): String {
    return "от $from до $to ${currency.orEmpty()}"
}

@Composable
fun ContactItem(contact: String, isEmail: Boolean) {
    val context = LocalContext.current

    Text(
        text = contact,
        modifier = Modifier.clickable {
            // Сделать клик по email и телефону
        }
    )
}
