package com.itzephir.whererubles.feature.income.presentation.mapper

import com.itzephir.whererubles.core.format.formatAmount
import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.core.model.string
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
        amount = amount.string,
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
        currency = currency.name,
    )

internal fun IncomeToday.toIncomeState(): IncomeState = IncomeState.Income(
    total.string.formatAmount(currency.name),
    income.map(Income::map),
)

internal fun IncomeByPeriod.toIncomeHistory(): IncomeHistoryState.IncomeHistory =
    IncomeHistoryState.IncomeHistory(
        total = total.string.formatAmount(currency.name),
        start = start,
        end = end,
        income = income.map { it.map() }
    )
