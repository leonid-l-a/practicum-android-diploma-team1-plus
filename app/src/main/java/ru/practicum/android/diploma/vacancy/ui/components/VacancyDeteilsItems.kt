package ru.practicum.android.diploma.vacancy.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.core.data.dto.VacancyDetail
import ru.practicum.android.diploma.vacancy.ui.viewmodel.VacancyViewModel

@Composable
fun ContactItem(
    contact: String,
    isEmail: Boolean,
    viewModel: VacancyViewModel,
) {
    val context = LocalContext.current

    Text(
        text = contact,
        modifier = Modifier.clickable {
            val intent = if (isEmail) {
                viewModel.writeWithMail()
            } else {
                viewModel.callWithPhone()
            }
            intent?.let { context.startActivity(it) }
        }
    )
}

@Composable
fun VacancyDescriptionItem(title: String, description: List<String>) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
        )

        HorizontalDivider(thickness = 4.dp, color = Color.Transparent)

        for (item in description) {
            Text(
                text = " • $item",
                style = MaterialTheme.typography.bodyLarge,
            )
        }

        HorizontalDivider(thickness = 12.dp, color = Color.Transparent)
    }
}

fun createDescriptionItemsList(vacancy: VacancyDetail): List<Pair<String, List<String>>> {
    return listOf(
        "График" to listOfNotNull(vacancy.schedule.name),
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
            .filter { it.isNotEmpty() }
    )
}
fun createContactItemsList(vacancy: VacancyDetail): List<Triple<String, Boolean?, Boolean>> {
    return listOfNotNull(
        vacancy.contacts.email.takeIf { it.isNotBlank() }?.let { Triple(it, true, true) },
        vacancy.contacts.phone?.firstOrNull()?.let { Triple(it, false, true) },
        vacancy.contacts.name.let { Triple(it, null, false) }
    )
}
