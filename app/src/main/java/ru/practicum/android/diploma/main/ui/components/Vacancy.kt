package ru.practicum.android.diploma.main.ui.components

import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import coil3.request.ImageRequest
import ru.practicum.android.diploma.core.ui.theme.ApplicationTheme
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.theme.BorderWidth
import ru.practicum.android.diploma.core.ui.theme.WrapperPaddingHorizontal
import ru.practicum.android.diploma.core.ui.theme.WrapperPaddingVertical
import ru.practicum.android.diploma.core.ui.theme.lightGray
import ru.practicum.android.diploma.main.ui.model.Vacancy


@Composable
fun VacancyItem(
    vacancy: Vacancy,
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
        /*Image(
            modifier = Modifier.size(48.dp)
                .clip(MaterialTheme.shapes.medium)
                .border(
                    BorderStroke(BorderWidth, lightGray),
                    MaterialTheme.shapes.medium
                ),
            painter = painterResource(R.drawable.item_placeholder),
            contentDescription = null
        )*/
        AsyncImage(
            modifier = Modifier
                .size(48.dp)
                .clip(MaterialTheme.shapes.medium)
                .border(
                    BorderStroke(BorderWidth, lightGray),
                    MaterialTheme.shapes.medium
                ),
            model = ImageRequest.Builder(LocalContext.current)
                .data(vacancy.logoUrl)
                .httpHeaders(NetworkHeaders.Builder().add("User-Agent", "Mozilla/5.0").build())
                .build(),
            placeholder = painterResource(R.drawable.item_placeholder),
            contentDescription = vacancy.name,
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            //vacancyName
            Text(
                vacancy.name,
                style = MaterialTheme.typography.titleLarge
            )
            //companyName
            Text(
                vacancy.industry,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.W400),
            )
            //salary
            Text(
                vacancy.salary,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.W400),
            )
        }
    }
}


@Composable
fun ShowVacancyList(
    vacancyList: List<Vacancy>,
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


@Preview(showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
private fun VacancyItemPreview() {
    ApplicationTheme {
        VacancyItem(
            vacancy = Vacancy(
                id = "af7dd6b8-2367-4695-82df-3470717cee2a",
                name = "Android Developer в Microsoft",
                logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Microsoft_logo.svg/1200px-Microsoft_logo.svg.png",
                industry = "Microsoft",
                salary = "1 000 р"
            ),
            modifier = Modifier,
        )
    }
}
