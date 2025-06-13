package com.itzephir.whererubles.feature.income.ui.component

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.itzephir.whererubles.feature.income.presentation.model.Income
import com.itzephir.whererubles.feature.income.presentation.model.IncomeId
import com.itzephir.whererubles.feature.income.presentation.state.IncomeState
import com.itzephir.whererubles.ui.theme.WhereRublesTheme

@Composable
fun IncomeScreenLayout(
    state: IncomeState,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.ime,
    ) { innerPadding ->
        when (state) {
            is IncomeState.Loading -> Loading(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )

            is IncomeState.Income  -> Income(
                income = state,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )
        }
    }
}

private val income = listOf(
    Income(
        id = IncomeId(0),
        title = "Зарплата",
        amount = "500 000 ₽",
    ),
    Income(
        id = IncomeId(1),
        title = "Подработка",
        amount = "100 000 ₽",
    ),
)

class IncomeStateParameterProvider : PreviewParameterProvider<IncomeState> {
    override val values: Sequence<IncomeState> = sequenceOf(
        IncomeState.Loading,
        IncomeState.Income(total = "0₽", income = emptyList()),
        IncomeState.Income(total = "600 000 ₽", income = income),
    )
}

@Preview(showBackground = true)
@Composable
private fun IncomeScreenLayoutPreview(
    @PreviewParameter(IncomeStateParameterProvider::class) state: IncomeState,
) {
    WhereRublesTheme {
        IncomeScreenLayout(state)
    }
}

