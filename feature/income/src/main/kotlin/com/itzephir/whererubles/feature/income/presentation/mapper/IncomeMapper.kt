package com.itzephir.whererubles.feature.income.presentation.mapper

import com.itzephir.whererubles.feature.income.domain.model.Income
import com.itzephir.whererubles.feature.income.domain.model.IncomeByPeriod
import com.itzephir.whererubles.feature.income.domain.model.IncomeToday
import com.itzephir.whererubles.feature.income.presentation.model.IncomeId
import com.itzephir.whererubles.feature.income.presentation.state.IncomeHistoryState
import com.itzephir.whererubles.feature.income.presentation.state.IncomeState

internal fun Income.map(): com.itzephir.whererubles.feature.income.presentation.model.Income =
    com.itzephir.whererubles.feature.income.presentation.model.Income(
        id = IncomeId(id.value),
        icon = emoji,
        title = title,
        amount = amount,
        time = transactionDate,
        comment = comment,
    )

internal fun IncomeToday.toIncomeState(): IncomeState = IncomeState.Income(
    total,
    income.map(Income::map),
)

internal fun IncomeByPeriod.toIncomeHistory(): IncomeHistoryState.IncomeHistory =
    IncomeHistoryState.IncomeHistory(
        total = total,
        start = start,
        end = end,
        income = income.map { it.map() }
    )