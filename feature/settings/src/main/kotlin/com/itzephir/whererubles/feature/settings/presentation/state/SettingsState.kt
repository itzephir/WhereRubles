package com.itzephir.whererubles.feature.settings.presentation.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import pro.respawn.flowmvi.api.MVIState

@Parcelize
data class SettingsState(
    val darkTheme: Boolean = false,
) : MVIState, Parcelable