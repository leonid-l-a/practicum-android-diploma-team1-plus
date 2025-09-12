package ru.practicum.android.diploma.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.content.res.Configuration
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.components.model.FilterItem
import ru.practicum.android.diploma.core.ui.theme.ApplicationTheme
import ru.practicum.android.diploma.core.ui.theme.Height60
import ru.practicum.android.diploma.core.ui.theme.LineHeightMedium
import ru.practicum.android.diploma.core.ui.theme.LineHeightSmall

@Composable
private fun DrawContent(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    content: @Composable (checked: Boolean) -> Unit
) {
    Column(
        modifier = modifier.size(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        content(checked)
    }
}

@Composable
fun FilterItem(
    labelText: String,
    modifier: Modifier = Modifier,
    labelValue: String = "",
    checked: Boolean = false,
    content: @Composable (checked: Boolean) -> Unit
) {
    Column(
        modifier
            .height(Height60)
    ) {
        Row(
            modifier = modifier
                .padding(top = 6.dp, bottom = 6.dp, start = 16.dp, end = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            val filterParams = FilterParams.getParams(checked = checked)
            val label = filterParams.first
            val color = filterParams.second
            val maxLines = filterParams.third
            Column(
                modifier = Modifier
                    .padding(vertical = 6.dp)
                    .fillMaxHeight()
                    .weight(1F),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    color = color,
                    text = labelText,
                    style = label,
                    maxLines = maxLines,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = if (checked) LineHeightSmall else LineHeightMedium
                )
                if (checked && labelValue.isNotEmpty()) {
                    Text(
                        color = MaterialTheme.colorScheme.onBackground,
                        overflow = TextOverflow.Ellipsis,
                        text = labelValue,
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.W400
                        ),
                        maxLines = maxLines,
                    )
                }
            }
            DrawContent(checked = checked, content = content)
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun FilterCheckBoxItemPreview() {
    val items = listOf(
        FilterItem(
            textLabel = "Место работаМесто работаМесто работаМесто работаМесто работаМесто работа",
            textLabelValue = "Россия, МоскваРоссия, МоскваРоссия, МоскваРоссия, МоскваРоссия, МоскваРоссия, Москва",
            checked = true
        ),
        FilterItem(
            textLabel = "Отрасль",
            textLabelValue = "",
            checked = false
        ),
        FilterItem(
            textLabel = "Не показывать без зарплаты",
            textLabelValue = "",
            checked = false
        )
    )
    ApplicationTheme {
        Column {
            items.forEach {
                FilterItem(
                    labelText = it.textLabel,
                    labelValue = it.textLabelValue,
                    checked = it.checked
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

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun FilterRadioButtonItemPreview() {
    val items = listOf(
        FilterItem(
            textLabel = "Место работа",
            textLabelValue = "Россия, Москва",
            checked = true
        ),
        FilterItem(
            textLabel = "Отрасль",
            textLabelValue = "",
            checked = false
        ),
        FilterItem(
            textLabel = "Не показывать без зарплаты",
            textLabelValue = "",
            checked = false
        )
    )
    ApplicationTheme {
        Column {
            items.forEach {
                FilterItem(
                    labelText = it.textLabel,
                    labelValue = it.textLabelValue,
                    checked = it.checked
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

