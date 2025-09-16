package ru.practicum.android.diploma.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.components.model.FilterItem
import ru.practicum.android.diploma.core.ui.theme.ApplicationTheme
import ru.practicum.android.diploma.core.ui.theme.Height60
import ru.practicum.android.diploma.core.ui.theme.LineHeightMedium19
import ru.practicum.android.diploma.core.ui.theme.LineHeightSmall16

/**
 * Это реализация поля FilterItem, используйте его как хотите: в LazyColumn или просто в Column.
 *
 * 1.У каждого экрана фильтра есть поля, которые можно сбросить, так вот это поля, которые имют isMainField = true
 * Остальные поля нельзя сбросить, по ним можно чисто кликнуть для навигации или какого-то другого действия (checkbox, radiobutton).
 * Если поле isMainField = true, то ему имплементим лямбду onClear для сброса значений, а также onClick, чтобы в потом мы смогли перейти на следующий экран фильтрации
 * Последняя лямбда компонента это контент, чтобы описывать, что мы хотим добавить: иконку, checkbox, radiobutton или еще что-то.
 *
 * 2. Кликать будем только по ">" или "X"
 *
 * 3. У компонента два обработчика события клика на кнопки из п.2.
 *
 * 4. Т.к. мы передаем между экранами строки, потому лямбда onClick принимает String в качестве параметра.
 *
 * 5. Поле имеет тип: checkbox, radiobutton, text. По умолчанию text, т.к. данный тип чаще всего используется в приложении.
 *
 * 6. Тип поля влияет на поведение: обработчиков событий, стили и т.д. (данный пункт будет дорабаться в рамках других задач, т.к. сейчас необходимо заиплеменить поведение для типа TEXT)
 */

private val demoItems = listOf(
    FilterItem(
        idValue = "1111",
        textLabel = "Место работа",
        textLabelValue = "Россия, Москва",
        checked = true,
        isMainField = true
    ),
    FilterItem(
        idValue = "2222",
        textLabel = "Отрасль",
        textLabelValue = "IT",
        checked = false,
        isMainField = true
    ),
    FilterItem(
        idValue = "3333",
        textLabel = "Не показывать без зарплаты",
        textLabelValue = "",
        checked = false,
        isMainField = true,
        typeField = FilterParams.FIELDTYPE.CHECK_BOX
    ),
    FilterItem(
        idValue = "4444",
        textLabel = "Россия",
        textLabelValue = "",
        checked = false
    )
)

@Composable
private fun DrawContent(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    content: @Composable (checked: Boolean) -> Unit
) {
    Column(
        modifier = modifier
            .size(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        content(checked)
    }
}

@Composable
private fun Modifier.handleClick(
    fieldType: FilterParams.FIELDTYPE,
    checked: Boolean,
    idValue: String = "",
    onClick: ((String) -> Unit)? = null,
    onToggle: ((Boolean) -> Unit)? = null,
): Modifier {
    return if (fieldType == FilterParams.FIELDTYPE.RADIO_BUTTON) {
        this
    } else {
        this
            .clickable {
                if (fieldType == FilterParams.FIELDTYPE.CHECK_BOX) {
                    onToggle?.invoke(!checked)
                } else if (fieldType == FilterParams.FIELDTYPE.TEXT) {
                    /*if (!checked) {
                        onClick?.invoke(idValue)
                    }*/
                    onClick?.invoke(idValue)
                }
            }
    }
}

@Composable
fun FilterItem(
    labelText: String,
    modifier: Modifier = Modifier,
    isMainField: Boolean = false,
    idValue: String = "",
    labelValue: String = "",
    checked: Boolean = false,
    onClick: ((String?) -> Unit)? = null,
    onClear: (() -> Unit)? = null,
    onToggle: ((Boolean) -> Unit)? = null,
    fieldType: FilterParams.FIELDTYPE = FilterParams.FIELDTYPE.TEXT,
    content: @Composable (checked: Boolean) -> Unit
) {
    Column(
        modifier = modifier
            .height(Height60)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 6.dp, bottom = 6.dp, start = 16.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            val defaultFilterParams = FilterParams.getParams(checked = checked)
            val defaultLabel = defaultFilterParams.first
            val defaultColor = defaultFilterParams.second
            val defaultMaxLines = defaultFilterParams.third
            val fieldParams = when (fieldType) {
                FilterParams.FIELDTYPE.TEXT -> {
                    Triple(
                        first = if (isMainField) {
                            defaultLabel
                        } else {
                            MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W400)
                        },
                        second = if (isMainField) {
                            defaultColor
                        } else {
                            MaterialTheme.colorScheme.onBackground
                        },
                        third = defaultMaxLines,
                    )
                }

                FilterParams.FIELDTYPE.CHECK_BOX -> {
                    Triple(
                        first = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W400),
                        second = MaterialTheme.colorScheme.onBackground,
                        third = FilterParams.ONE_LINE,
                    )
                }

                FilterParams.FIELDTYPE.RADIO_BUTTON -> {
                    Triple(
                        first = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W400),
                        second = MaterialTheme.colorScheme.onBackground,
                        third = FilterParams.TWO_LINE,
                    )
                }
            }
            val modifierClick = Modifier.handleClick(
                fieldType = fieldType,
                checked = checked,
                onToggle = onToggle,
                onClick = onClick,
                idValue = idValue
            )
            Column(
                modifier = modifierClick
                    .fillMaxHeight()
                    .weight(1F),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    color = fieldParams.second,
                    text = labelText,
                    style = fieldParams.first,
                    maxLines = fieldParams.third,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = if (checked) LineHeightSmall16 else LineHeightMedium19
                )
                if (checked && labelValue.isNotEmpty()) {
                    Text(
                        color = MaterialTheme.colorScheme.onBackground,
                        overflow = TextOverflow.Ellipsis,
                        text = labelValue,
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.W400
                        ),
                        maxLines = fieldParams.third,
                    )
                }
            }
            DrawContent(
                modifier = Modifier.clickable {
                    if (checked) {
                        onClear?.invoke()
                    } else {
                        onClick?.invoke(idValue)
                    }
                },
                checked = checked,
                content = content,
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun FilterCheckBoxItemPreview() {
    var filterItems by remember { mutableStateOf(demoItems) }
    ApplicationTheme {
        LazyColumn {
            items(items = filterItems, key = { it.idValue }) { filterItem ->
                FilterItem(
                    labelText = filterItem.textLabel,
                    labelValue = filterItem.textLabelValue,
                    checked = filterItem.checked,
                    onClick = {
                        filterItems = filterItems.map {
                            if (filterItem.idValue == it.idValue) {
                                filterItem.copy(checked = !filterItem.checked)
                            } else {
                                it
                            }
                        }
                    },
                    onClear = {
                        filterItems = filterItems.map {
                            if (filterItem.idValue == it.idValue) {
                                filterItem.copy(checked = !filterItem.checked)
                            } else {
                                it
                            }
                        }
                    },
                    isMainField = filterItem.isMainField,
                    fieldType = filterItem.typeField
                ) { checked ->
                    when (filterItem.typeField) {
                        FilterParams.FIELDTYPE.TEXT -> {
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

                        FilterParams.FIELDTYPE.CHECK_BOX -> {
                            Checkbox(
                                checked = true,
                                onCheckedChange = {}
                            )
                        }

                        FilterParams.FIELDTYPE.RADIO_BUTTON -> {}
                    }
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
    ApplicationTheme {
        Column {
            demoItems.forEach {
                FilterItem(
                    labelText = it.textLabel,
                    labelValue = it.textLabelValue,
                    checked = it.checked,
                    onClick = {},
                    isMainField = it.isMainField,
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
