package ru.practicum.android.diploma.main.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import coil3.request.ImageRequest
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.theme.BorderWidth
import ru.practicum.android.diploma.core.ui.theme.WrapperPaddingHorizontal
import ru.practicum.android.diploma.core.ui.theme.WrapperPaddingVertical
import ru.practicum.android.diploma.core.ui.theme.lightGray
import ru.practicum.android.diploma.main.data.model.VacancyDetailMainData

@Composable
fun VacancyItem(
    vacancy: VacancyDetailMainData,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    Row(
        modifier = modifier
            .padding(vertical = WrapperPaddingVertical)
            .fillMaxWidth()
            .clickable { onClick(vacancy.id) },
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        AsyncImage(
            modifier = Modifier
                .size(48.dp)
                .clip(MaterialTheme.shapes.medium)
                .border(
                    BorderStroke(BorderWidth, lightGray),
                    MaterialTheme.shapes.medium
                ),
            model = ImageRequest.Builder(LocalContext.current)
                .data(vacancy.employer.logo)
                .httpHeaders(NetworkHeaders.Builder().add("User-Agent", "Mozilla/5.0").build())
                .build(),
            placeholder = painterResource(R.drawable.item_placeholder),
            contentDescription = vacancy.name,
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                vacancy.name,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                vacancy.employer.name,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.W400),
            )
            Text(
                text = "Много",
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.W400),
            )
        }
    }
}

@Composable
fun ShowVacancyList(
    vacancyList: List<VacancyDetailMainData>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = WrapperPaddingHorizontal)
            .fillMaxSize()
    ) {
        items(vacancyList) {
            VacancyItem(vacancy = it, onClick = onClick)
        }
    }
}
