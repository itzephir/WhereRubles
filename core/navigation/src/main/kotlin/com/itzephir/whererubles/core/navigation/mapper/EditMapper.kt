package com.itzephir.whererubles.core.navigation.mapper

import android.icu.number.Precision.currency
import com.itzephir.whererubles.core.navigation.AppGraph
import com.itzephir.whererubles.expenses.presentation.model.Expense
import com.itzephir.whererubles.feature.income.presentation.model.Income
import com.itzephir.whererubles.feature.transactionEditor.presentation.model.Transaction
import com.itzephir.whererubles.feature.transactionEditor.presentation.model.TransactionId

internal fun Expense.toEdit(): AppGraph.Expenses.ExpensesRoutes.Edit =
    AppGraph.Expenses.ExpensesRoutes.Edit(
        transactionId = TransactionId(id.value),
        transaction = Transaction(
            account = Transaction.Account(
                id = Transaction.AccountId(account.id.value),
                name = account.name,
            ),
            category = Transaction.Category(
                id = Transaction.CategoryId(category.id.value),
                name = category.name
            ),
            amount = amount,
            transactionDate = time,
            comment = comment,
        ),
        currency = currency
    )

internal fun Income.toEdit() : AppGraph.Income.IncomeRoutes.Edit =
    AppGraph.Income.IncomeRoutes.Edit(
        transactionId = TransactionId(id.value),
        transaction = Transaction(
            account = Transaction.Account(
                id = Transaction.AccountId(account.id.value),
                name = account.name,
            ),
            category = Transaction.Category(
                id = Transaction.CategoryId(category.id.value),
                name = category.name
            ),
            amount = amount,
            transactionDate = time,
            comment = comment,
        ),
        currency = currency
    )