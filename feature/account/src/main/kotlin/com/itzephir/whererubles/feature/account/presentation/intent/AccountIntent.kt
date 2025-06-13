package com.itzephir.whererubles.feature.account.presentation.intent

import com.itzephir.whererubles.feature.account.presentation.action.AccountAction
import com.itzephir.whererubles.feature.account.presentation.state.AccountState
import pro.respawn.flowmvi.dsl.LambdaIntent

typealias AccountIntent = LambdaIntent<AccountState, AccountAction>