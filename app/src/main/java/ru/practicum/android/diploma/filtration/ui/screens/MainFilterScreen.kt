package ru.practicum.android.diploma.filtration.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.components.FilterItem

@Composable
fun MainFilterScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        if (false) {
            var isFocused by remember { mutableStateOf(false) }
            TextField(
                value = "",
                onValueChange = { },
                label = {
                    Column {
                        Text("Email")
                        if (!isFocused) {
                            Text("user@example.com")
                        }

                    }
                },
                placeholder = {
                    Text("user@example.com")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        isFocused
                    }
            )
        }
        FilterItem(
            labelText = "Место работы",
            checked = false
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
            labelText = "Отрасль",
            checked = false
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
            labelText = "Отрасль",
            checked = false
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
