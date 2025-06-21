package com.itzephir.whererubles.feature.settings.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.itzephir.whererubles.feature.settings.presentation.state.SettingsState
import com.itzephir.whererubles.ui.TopBar
import com.itzephir.whererubles.ui.theme.WhereRublesTheme
import kotlinx.coroutines.launch

@Composable
fun SettingsScreenLayout(
    state: SettingsState,
    onDarkThemeChanged: (Boolean) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Настройки",
            )
        },
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.ime,
    ) { innerPadding ->

        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .border(0.5.dp, Color.Gray),
        ) {
            SettingItem(
                title = "Тёмная тема",
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    coroutineScope.launch {
                        onDarkThemeChanged(!state.darkTheme)
                    }
                }
            ) {
                Switch(checked = state.darkTheme, onCheckedChange = onDarkThemeChanged)
            }

            SettingItem(
                title = "Основной цвет",
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                        contentDescription = "Основной цвет",
                    )
                }
            }

            SettingItem(
                title = "Звуки",
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                        contentDescription = "Звуки",
                    )
                }
            }

            SettingItem(
                title = "Хаптики",
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                        contentDescription = "Хаптики",
                    )
                }
            }

            SettingItem(
                title = "Код пароль",
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                        contentDescription = "Код пароль",
                    )
                }
            }

            SettingItem(
                title = "Синхронизация",
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                        contentDescription = "Синхронизация",
                    )
                }
            }

            SettingItem(
                title = "Язык",
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                        contentDescription = "Язык",
                    )
                }
            }

            SettingItem(
                title = "О программе",
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                        contentDescription = "О программе",
                    )
                }
            }
        }
    }
}

class SettingsStateParameterProvider : PreviewParameterProvider<SettingsState> {
    override val values: Sequence<SettingsState> = sequenceOf(
        SettingsState(false),
        SettingsState(true),
    )
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenLayoutPreview(
    @PreviewParameter(SettingsStateParameterProvider::class) state: SettingsState,
) {
    WhereRublesTheme {
        SettingsScreenLayout(state = state, onDarkThemeChanged = {})
    }
}

