package com.itzephir.whererubles.feature.income.presentation.mapper

import com.itzephir.whererubles.core.format.formatAmount
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
        account = com.itzephir.whererubles.feature.income.presentation.model.Income.Account(
            id = com.itzephir.whererubles.feature.income.presentation.model.Income.Account.AccountId(
                account.id.value
            ),
            name = account.name,
        ),
        category = com.itzephir.whererubles.feature.income.presentation.model.Income.Category(
            id = com.itzephir.whererubles.feature.income.presentation.model.Income.Category.CategoryId(
                category.id.value
            ),
            name = category.name,
        ),
        currency = currency,
    )

internal fun IncomeToday.toIncomeState(): IncomeState = IncomeState.Income(
    total.formatAmount(currency),
    income.map(Income::map),
)

internal fun IncomeByPeriod.toIncomeHistory(): IncomeHistoryState.IncomeHistory =
    IncomeHistoryState.IncomeHistory(
        total = total.formatAmount(currency),
        start = start,
        end = end,
        income = income.map { it.map() }
    )
