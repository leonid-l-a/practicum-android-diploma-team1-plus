package ru.practicum.android.diploma.vacancy.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.core.data.dto.VacancyDetail
import ru.practicum.android.diploma.vacancy.ui.viewmodel.VacancyViewModel

@Composable
fun VacancyHeader(vacancy: VacancyDetail) {
    HorizontalDivider(thickness = 16.dp, color = Color.Transparent)

    Text(
        text = vacancy.name,
        style = MaterialTheme.typography.headlineLarge
    )
    Text(
        text = vacancy.salary.toDisplayString(),
        style = MaterialTheme.typography.headlineMedium
    )

    HorizontalDivider(thickness = 16.dp, color = Color.Transparent)
}



@Composable
fun ExperienceSection(vacancy: VacancyDetail) {
    Text(
        "Требуемый опыт",
        style = MaterialTheme.typography.headlineSmall
    )

    HorizontalDivider(thickness = 4.dp, color = Color.Transparent)

    Text(
        vacancy.experience.name,
        style = MaterialTheme.typography.bodyLarge
    )

    HorizontalDivider(thickness = 8.dp, color = Color.Transparent)

    Text(
        vacancy.employment.name + ", " + "Удалённая работа",
        style = MaterialTheme.typography.bodyLarge
    )

    HorizontalDivider(thickness = 32.dp, color = Color.Transparent)
}

@Composable
fun DescriptionSection(vacancy: VacancyDetail, viewModel: VacancyViewModel) {
    Text(
        "Описание вакансии",
        style = MaterialTheme.typography.headlineMedium
    )

    HorizontalDivider(thickness = 16.dp, color = Color.Transparent)

    val otherItems = createDescriptionItemsList(vacancy)
    val contactItems = createContactItemsList(vacancy)

    otherItems.forEach { (title, desc) ->
        if (desc.isNotEmpty()) {
            VacancyDescriptionItem(title = title, description = desc)
        }
    }

    ContactsSection(contactItems, viewModel)

    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun ContactsSection(contactItems: List<Triple<String, Boolean?, Boolean>>, viewModel: VacancyViewModel) {
    if (contactItems.isNotEmpty()) {
        Text(
            "Контакты (кликните, чтобы связаться)",
            style = MaterialTheme.typography.headlineSmall
        )

        HorizontalDivider(thickness = 4.dp, color = Color.Transparent)

        contactItems.forEach { (value, isEmail) ->
            when (isEmail) {
                true -> ContactItem(contact = value, isEmail = true, viewModel = viewModel)
                false -> ContactItem(contact = value, isEmail = false, viewModel = viewModel)
                else -> Text(text = value)
            }
        }
    }
}
