package com.itzephir.whererubles.feature.income.ui.screen

import androidx.compose.runtime.Composable
import com.itzephir.whererubles.feature.income.di.DaggerIncomeFeatureComponent
import com.itzephir.whererubles.feature.income.di.IncomeFeatureDependencies
import com.itzephir.whererubles.feature.income.presentation.model.Income
import com.itzephir.whererubles.feature.income.ui.navigation.IncomeNavigation

@Composable
fun IncomeScreen(
    incomeFeatureDependencies: IncomeFeatureDependencies,
    onIncomeClick: (Income) -> Unit,
) {
    val incomeFeatureComponent =
        DaggerIncomeFeatureComponent.factory().create(incomeFeatureDependencies)

    val incomeFeatureContext = incomeFeatureComponent.incomeFeatureContext

    IncomeNavigation(incomeFeatureContext, onIncomeClick)
}

