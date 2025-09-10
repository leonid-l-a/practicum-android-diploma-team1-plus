package ru.practicum.android.diploma.vacancy.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.data.dto.vacancydetails.Salary
import ru.practicum.android.diploma.vacancy.ui.state.VacancyState
import ru.practicum.android.diploma.vacancy.ui.viewmodel.VacancyViewModel

@Composable
fun VacancyScreen(viewModel: VacancyViewModel, navController: NavController) {
    val horizontalPadding = 16.dp
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = { TopBar(viewModel, navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = horizontalPadding)
                .verticalScroll(rememberScrollState())
        ) {
            when (state) {
                is VacancyState.Loading -> LoadingState()
                is VacancyState.ServerError -> ServerErrorState()
                is VacancyState.Error -> ErrorState()
                is VacancyState.Success -> {
                    val vacancyDetail = (state as VacancyState.Success).vacancyDetail
                    SuccessState(vacancyDetail, viewModel)
                }
            }
        }
    }
}
fun Salary.toDisplayString(): String {
    return when {
        from != null && to != null -> {
            "от $from до $to ${currency.orEmpty()}"
        }
        from != null ->
            "от $from ${currency.orEmpty()}"
        to != null ->
            "до $to ${currency.orEmpty()}"
        else ->
            R.string.salary_not_specified.toString()
    }
}
