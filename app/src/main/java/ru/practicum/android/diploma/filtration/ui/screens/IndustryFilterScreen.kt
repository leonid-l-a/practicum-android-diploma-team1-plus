package ru.practicum.android.diploma.filtration.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.components.ErrorResult
import ru.practicum.android.diploma.core.ui.components.FilterButton
import ru.practicum.android.diploma.core.ui.components.FilterItem
import ru.practicum.android.diploma.core.ui.components.FilterParams
import ru.practicum.android.diploma.core.ui.theme.Height24
import ru.practicum.android.diploma.core.ui.theme.Height60
import ru.practicum.android.diploma.core.ui.theme.WrapperPaddingHorizontal16
import ru.practicum.android.diploma.core.ui.theme.blue
import ru.practicum.android.diploma.filtration.ui.components.SearchFilterItems
import ru.practicum.android.diploma.filtration.ui.components.TopBar
import ru.practicum.android.diploma.filtration.ui.model.data.Industry
import ru.practicum.android.diploma.filtration.ui.state.IndustryState
import ru.practicum.android.diploma.filtration.ui.viewmodel.IndustryViewModel

@Composable
fun IndustryFilterScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    vm: IndustryViewModel = koinViewModel()
) {
    Scaffold(
        topBar = {
            TopBar(
                text = stringResource(R.string.choose_industry),
                onBackNavigate = {
                    navController?.popBackStack()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            var text by rememberSaveable { mutableStateOf("") }
            SearchFilterItems(
                text = text,
                onSearchHandler = {
                    text = it
                    vm.filerItems(text)
                },
                onResetRequest = {
                    text = ""
                    vm.filerItems(text)
                }
            )
            ShowContent(
                vm = vm,
            )
        }
    }
}

@Composable
private fun ShowContent(
    modifier: Modifier = Modifier,
    vm: IndustryViewModel = koinViewModel(),
    selectedIndustry: Industry? = null
) {
    val industryState by vm.industryState.collectAsState()
    var selectedValue by remember { mutableStateOf(selectedIndustry) }
    when (industryState) {
        is IndustryState.Idle -> {}
        is IndustryState.Loading -> {}
        is IndustryState.Content -> {
            ShowItems(
                selectedValue = selectedValue,
                industryItems = (industryState as IndustryState.Content).items,
                onSelectionChanged = { selectedValue = it }
            )
        }

        is IndustryState.EmptyResult -> ShowError()
    }

}

@Composable
private fun ShowItems(
    modifier: Modifier = Modifier,
    selectedValue: Industry? = null,
    onSelectionChanged: (Industry?) -> Unit,
    industryItems: List<Industry>,
) {
    //val (selectedOption, onOptionSelected) = remember { mutableStateOf(industryItems[0]) }
    Column {
        LazyColumn(
            modifier = modifier
                .selectableGroup()
                .weight(1F)
        ) {
            items(items = industryItems) { radioItem ->
                FilterItem(
                    modifier = Modifier
                        .selectable(
                            selected = radioItem == selectedValue,
                            onClick = { onSelectionChanged(radioItem) },
                            role = Role.RadioButton
                        ),
                    labelText = radioItem.name,
                    fieldType = FilterParams.FIELDTYPE.RADIO_BUTTON,
                ) {
                    RadioButton(
                        onClick = null,
                        selected = radioItem == selectedValue,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = blue,
                            unselectedColor = blue,
                        )
                    )
                }
            }
        }
        selectedValue?.let {
            FilterButton(
                modifier = Modifier
                    .padding(
                        bottom = Height24,
                        start = WrapperPaddingHorizontal16,
                        end = WrapperPaddingHorizontal16
                    )
                    .height(Height60)
                    .fillMaxWidth(),

                textButton = stringResource(R.string.filter_add),
                onClick = {}
            )
        }
    }
}

@Composable
private fun ShowError(
    modifier: Modifier = Modifier,
) {
    ErrorResult(
        textRes = R.string.choose_industry_not_exist,
        painterRes = R.drawable.favorites_error
    )
}
