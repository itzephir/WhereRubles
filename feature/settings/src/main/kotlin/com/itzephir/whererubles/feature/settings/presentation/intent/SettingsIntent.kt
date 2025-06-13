package com.itzephir.whererubles.feature.settings.presentation.intent

import com.itzephir.whererubles.feature.settings.presentation.action.SettingsAction
import com.itzephir.whererubles.feature.settings.presentation.state.SettingsState
import pro.respawn.flowmvi.dsl.LambdaIntent

typealias SettingsIntent = LambdaIntent<SettingsState, SettingsAction>
