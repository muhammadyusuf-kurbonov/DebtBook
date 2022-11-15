package uz.qmgroup.debtbook.ui.screens.transactionDialog

import android.content.res.Configuration
import android.icu.text.NumberFormat
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.qmgroup.debtbook.domain.models.Contact
import uz.qmgroup.debtbook.ui.theme.DebtBookTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDialogContent(
    modifier: Modifier = Modifier,
    contact: Contact?,
    balanceAsString: String,
    onBalanceChange: (String) -> Unit,
    cancel: () -> Unit,
    save: (add: Boolean) -> Unit,
    saveDisabled: Boolean = false
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "New contact", style = MaterialTheme.typography.headlineSmall)

            Text(text = "Name: ${contact?.name ?: "Loading..."}", style = MaterialTheme.typography.bodyLarge)

            Text(text = contact?.let { "Current balance: ${NumberFormat.getCurrencyInstance().format(it.balance)}" } ?: "Current balance: Loading ...", style = MaterialTheme.typography.bodyLarge)

            OutlinedTextField(
                value = balanceAsString, onValueChange = onBalanceChange, label = {
                    Text(text = "Sum")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 8.dp)
            ) {
                TextButton(onClick = cancel) {
                    Text(text = "Cancel")
                }

                TextButton(onClick = { save(false) }, enabled = !saveDisabled) {
                    Text(text = "Return", color =
                        if (isSystemInDarkTheme()) Color(0xFFE00000)
                        else Color(0xFF8B0000)
                    )
                }
                TextButton(onClick = { save(true) }, enabled = !saveDisabled) {
                    Text(
                        text = "Debt", color =
                        if (isSystemInDarkTheme())
                            Color(0xFF00F000)
                        else Color(0xFF008B00)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ContactDialogContentPreview() {
    DebtBookTheme {
        TransactionDialogContent(
            contact = Contact("Jake", balance = 15f),
            balanceAsString = "$0",
            onBalanceChange = {},
            cancel = {},
            save = {}
        )
    }
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ContactDialogContentLoadingPreview() {
    DebtBookTheme {
        TransactionDialogContent(
            contact = null,
            balanceAsString = "$0",
            onBalanceChange = {},
            cancel = {},
            save = {}
        )
    }
}