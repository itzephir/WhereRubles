package com.itzephir.whererubles.feature.account.ui.component

import android.R.attr.label
import android.R.attr.text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CurrencyRuble
import androidx.compose.material.icons.filled.Euro
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.itzephir.whererubles.feature.account.presentation.model.Currency
import com.itzephir.whererubles.feature.account.presentation.state.UpdateAccountState
import com.itzephir.whererubles.ui.SingleItem
import com.itzephir.whererubles.ui.SingleItemColors.Companion.singleItemColors

@Composable
fun AccountForm(
    account: UpdateAccountState.Form,
    modifier: Modifier = Modifier,
    onChange: (String, String) -> Unit,
) {
    Column(modifier = modifier) {
        Column {
            var name by remember { mutableStateOf(account.name) }
            var balance by remember { mutableStateOf(account.balance) }

            LaunchedEffect(name, balance) {
                onChange(name, balance)
            }

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(text = "Имя")
                },
                shape = RectangleShape,
            )
            HorizontalDivider()

            OutlinedTextField(
                value = balance,
                onValueChange = { balance = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.AttachMoney,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(text = "Баланс")
                },
                shape = RectangleShape,
            )
            HorizontalDivider()
        }
    }
}
