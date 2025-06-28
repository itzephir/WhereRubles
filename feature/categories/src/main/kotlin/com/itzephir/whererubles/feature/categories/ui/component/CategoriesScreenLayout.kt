package com.itzephir.whererubles.feature.categories.ui.component

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
import com.itzephir.whererubles.feature.categories.presentation.model.Category
import com.itzephir.whererubles.feature.categories.presentation.model.CategoryId
import com.itzephir.whererubles.feature.categories.presentation.model.SearchState
import com.itzephir.whererubles.feature.categories.presentation.state.CategoriesState
import com.itzephir.whererubles.feature.categories.presentation.state.CategoriesState.Categories
import com.itzephir.whererubles.feature.categories.presentation.state.CategoriesState.Loading
import com.itzephir.whererubles.ui.TopBar
import com.itzephir.whererubles.ui.theme.WhereRublesTheme

@Composable
fun CategoriesScreenLayout(
    state: CategoriesState,
    onSearchStateChanged: (SearchState) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                title = "Мои статьи",
            )
        },
        contentWindowInsets = WindowInsets.ime,
    ) { innerPadding ->
        when (state) {
            is Loading    -> Loading(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )

            is Categories -> Categories(
                categories = state,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                onSearchStateChanged = onSearchStateChanged,
            )
        }
    }
}

@Suppress("MagicNumber")
private val categories = listOf(
    Category(
        id = CategoryId(0),
        icon = "\uD83C\uDFE0",
        title = "Аренда квартиры",
    ),
    Category(
        id = CategoryId(1),
        icon = "\uD83D\uDC57",
        title = "Одежда",
    ),
    Category(
        id = CategoryId(2),
        icon = "\uD83D\uDC36",
        title = "На собачку",
    ),
    Category(
        id = CategoryId(3),
        icon = "РК",
        title = "Ремонт квартиры",
    ),
    Category(
        id = CategoryId(4),
        icon = "\uD83C\uDF6D",
        title = "Продукты",
    ),
    Category(
        id = CategoryId(5),
        icon = "\uD83C\uDFCB\uFE0F",
        title = "Спортзал",
    ),
    Category(
        id = CategoryId(6),
        icon = "\uD83D\uDC8A",
        title = "Медицина",
    ),
    Category(
        id = CategoryId(7),
        icon = "\uD83D\uDCB0",
        title = "Зарплата",
    ),
    Category(
        id = CategoryId(8),
        icon = "\uD83D\uDCBB",
        title = "Подработка",
    ),
)

private class CategoriesStateParameterProvider : PreviewParameterProvider<CategoriesState> {
    override val values: Sequence<CategoriesState> = sequenceOf(
        Loading,
        Categories(searchState = SearchState(""), categories = emptyList()),
        Categories(searchState = SearchState(""), categories = categories),
        Categories(searchState = SearchState("blabla"), categories = categories)
    )
}

@Preview(showBackground = true)
@Composable
private fun CategoriesScreenLayoutPreview(
    @PreviewParameter(CategoriesStateParameterProvider::class) state: CategoriesState,
) {
    WhereRublesTheme {
        CategoriesScreenLayout(state, onSearchStateChanged = {})
    }
}

