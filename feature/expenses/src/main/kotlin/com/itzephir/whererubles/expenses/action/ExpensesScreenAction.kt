package com.itzephir.whererubles.expenses.action

import android.os.Parcelable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

sealed interface ExpensesScreenActions: Parcelable {

}

internal val expensesActionChannel = Channel<ExpensesScreenActions>()
val expensesActionFlow = expensesActionChannel.receiveAsFlow()