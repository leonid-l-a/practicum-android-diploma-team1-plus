package ru.practicum.android.diploma.command

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.command.components.ApplicationDevelopers
import ru.practicum.android.diploma.command.components.CommandBar
import ru.practicum.android.diploma.core.ui.theme.ApplicationTheme

@Composable
fun CommandScreen(
    modifier: Modifier = Modifier
) {
        Scaffold(
            topBar = { CommandBar(modifier) },
            content = { innerPadding ->
                ApplicationDevelopers(
                    modifier = modifier,
                    innerPadding = innerPadding,
                )
            }
        )
    }

@Preview
@Composable
fun CommandScreenPreview() {
    ApplicationTheme { CommandScreen() }
}
