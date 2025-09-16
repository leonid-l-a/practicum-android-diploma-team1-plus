package ru.practicum.android.diploma.filtration.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.domain.repository.StorageKey
import ru.practicum.android.diploma.core.navigation.Screen
import ru.practicum.android.diploma.core.ui.components.FilterButton
import ru.practicum.android.diploma.core.ui.components.FilterItem
import ru.practicum.android.diploma.core.ui.components.FilterParams
import ru.practicum.android.diploma.core.ui.theme.ApplicationTheme
import ru.practicum.android.diploma.core.ui.theme.Height24
import ru.practicum.android.diploma.core.ui.theme.Height60
import ru.practicum.android.diploma.core.ui.theme.SpacerHeight24
import ru.practicum.android.diploma.core.ui.theme.SpacerHeight8
import ru.practicum.android.diploma.core.ui.theme.WrapperPaddingHorizontal16
import ru.practicum.android.diploma.core.ui.theme.WrapperPaddingVertical16
import ru.practicum.android.diploma.core.ui.theme.blackUniversal
import ru.practicum.android.diploma.core.ui.theme.blue
import ru.practicum.android.diploma.core.ui.theme.red
import ru.practicum.android.diploma.filtration.domain.model.hasActiveFilters
import ru.practicum.android.diploma.filtration.ui.components.SalaryField
import ru.practicum.android.diploma.filtration.ui.components.TopBar
import ru.practicum.android.diploma.filtration.ui.viewmodel.MainFilterViewModel
import ru.practicum.android.diploma.main.ui.viewmodel.SearchVacancyViewModel

@Composable
fun MainFilterScreen(
    modifier: Modifier = Modifier,
    vm: MainFilterViewModel = koinViewModel(),
    searchVm: SearchVacancyViewModel = koinViewModel(),
    navController: NavController? = null
) {
    Scaffold(
        topBar = {
            TopBar(
                text = stringResource(R.string.filter_settings),
                onBackNavigate = {
                    navController?.popBackStack()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            val filterState by vm.stateFilter.collectAsState()

            Column(
                modifier = Modifier.padding(top = WrapperPaddingVertical16)
            ) {
                FilterItem(
                    labelText = stringResource(R.string.work_place),
                    labelValue = listOf(
                        filterState.countryValue,
                        filterState.regionValue
                    )
                        .filter { it.isNotEmpty() }
                        .joinToString(", "),
                    checked = filterState.countryValue.isNotEmpty() || filterState.regionValue.isNotEmpty(),
                    idValue = filterState.areaId,
                    isMainField = true,
                    onClick = {
                        navController?.navigate(Screen.WorkPlace.route)
                    },
                    onClear = {
                        vm.clearByKey(StorageKey.AREA_ID_KEY)
                        vm.clearByKey(StorageKey.COUNTRY_NAME_KEY)
                        vm.clearByKey(StorageKey.REGION_NAME_KEY)
                    }
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
                FilterItem(
                    labelText = stringResource(R.string.industry),
                    labelValue = filterState.industryValue,
                    checked = filterState.industryValue.isNotEmpty(),
                    isMainField = true,
                    idValue = filterState.industryId,
                    onClick = { industryId ->
                        navController?.navigate(
                            Screen.IndustrySelection.createRoute(
                                industryId = industryId
                            )
                        )
                    },
                    onClear = {
                        vm.clearByKey(key = StorageKey.INDUSTRY_ID_KEY)
                    }
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

                SalaryField(
                    modifier = Modifier
                        .padding(
                            horizontal = WrapperPaddingHorizontal16,
                            vertical = SpacerHeight24
                        ),
                    value = filterState.salaryValue,
                    onValueChange = { newText ->
                        vm.saveSalary(newText)
                    },
                    topPlaceholder = { topLabelColor ->
                        Text(
                            text = stringResource(R.string.need_salary),
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.W400),
                            color = topLabelColor
                        )
                    },
                    bottomPlaceholder = { bottomLabelColor ->
                        Text(
                            text = stringResource(R.string.input_salary),
                            color = bottomLabelColor,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    trailingIcon = {
                        if (filterState.salaryValue.isNotEmpty()) {
                            Icon(
                                modifier = Modifier
                                    .size(Height24)
                                    .clickable {
                                        vm.saveSalary("")
                                    },
                                painter = painterResource(R.drawable.close_24),
                                contentDescription = null,
                                tint = blackUniversal
                            )
                        }
                    }
                )

                FilterItem(
                    labelText = stringResource(R.string.not_show_without_salary),
                    checked = filterState.withSalary.isNotEmpty(),
                    isMainField = true,
                    fieldType = FilterParams.FIELDTYPE.CHECK_BOX,
                    onToggle = {
                        vm.saveWithSalary(withSalary = it)
                    }
                ) { checked ->
                    Checkbox(
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = blue,
                            checkedColor = blue,
                            checkmarkColor = MaterialTheme.colorScheme.background
                        ),
                        checked = checked,
                        onCheckedChange = {
                            vm.saveWithSalary(withSalary = it)
                        },
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(
                        bottom = Height24,
                        start = WrapperPaddingHorizontal16,
                        end = WrapperPaddingHorizontal16
                    ),
                verticalArrangement = Arrangement.spacedBy(SpacerHeight8)
            ) {
                FilterButton(
                    modifier = Modifier
                        .height(Height60)
                        .fillMaxWidth(),
                    textButton = stringResource(R.string.filter_apply),
                    onClick = {
                        searchVm.setShouldRepeatRequest(shouldRepeat = true)
                        navController?.popBackStack()
                    }
                )

                if (filterState.hasActiveFilters()) {
                    FilterButton(
                        modifier = Modifier
                            .height(Height60)
                            .fillMaxWidth(),
                        textColor = red,
                        containerColor = Color.Transparent,
                        textButton = stringResource(R.string.filter_reset),
                        onClick = {
                            vm.clearStorage()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MainFilterScreenPreview() {
    ApplicationTheme {
        MainFilterScreen()
    }
}
