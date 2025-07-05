package com.itzephir.whererubles.feature.account.presentation.intent

import com.itzephir.whererubles.feature.account.presentation.action.UpdateAccountAction
import com.itzephir.whererubles.feature.account.presentation.state.UpdateAccountState
import pro.respawn.flowmvi.dsl.LambdaIntent

typealias UpdateAccountIntent = LambdaIntent<UpdateAccountState, UpdateAccountAction>