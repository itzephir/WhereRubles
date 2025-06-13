package com.itzephir.whererubles.feature.categories.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itzephir.whererubles.feature.categories.presentation.model.Category
import com.itzephir.whererubles.feature.categories.presentation.model.CategoryId
import com.itzephir.whererubles.feature.categories.presentation.model.SearchState
import com.itzephir.whererubles.feature.categories.presentation.state.CategoriesState
import com.itzephir.whererubles.ui.theme.WhereRublesTheme

@Composable
fun Categories(
    categories: CategoriesState.Categories,
    modifier: Modifier = Modifier,
    onSearchStateChanged: (SearchState) -> Unit,
) {
    Column(modifier = modifier) {
        var textFieldState by rememberSaveable {
            mutableStateOf("")
        }

        LaunchedEffect(Unit) {
            textFieldState = categories.searchState.value
        }

        LaunchedEffect(textFieldState) {
            onSearchStateChanged(SearchState(textFieldState))
        }

        TextField(
            value = textFieldState,
            onValueChange = { textFieldState = it },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                    )
                }
            },
            placeholder = {
                Text(text = "Найти статью")
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items = categories.categories, key = { it.id }) { expense ->
                CategoryItem(
                    category = expense,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 64.dp),
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CategoriesPreview() {
    WhereRublesTheme {
        Categories(
            CategoriesState.Categories(
                searchState = SearchState(""),
                categories = listOf(
                    Category(
                        id = CategoryId(0),
                        icon = "\uD83D\uDE08",
                        title = "Расхоооод",
                    )
                )
            ),
            modifier = Modifier.fillMaxSize(),
            onSearchStateChanged = {},
        )
    }
}