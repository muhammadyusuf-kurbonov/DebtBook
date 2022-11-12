package uz.qmgroup.debtbook.ui.screens.contactDialog

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDialogContent(
    modifier: Modifier = Modifier,
    name: String,
    onNameChange: (String) -> Unit,
    balanceAsString: String,
    onBalanceChange: (String) -> Unit,
    cancel: () -> Unit,
    save: () -> Unit,
    saveDisabled: Boolean = false
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "New contact", style = MaterialTheme.typography.headlineSmall)

            OutlinedTextField(value = name, onValueChange = onNameChange, label = {
                Text(text = "Name")
            })

            OutlinedTextField(
                value = balanceAsString, onValueChange = onBalanceChange, label = {
                    Text(text = "Balance")
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
                TextButton(onClick = save, enabled = !saveDisabled) {
                    Text(text = "Save")
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ContactDialogContentPreview() {
    ContactDialogContent(name = "Joe",
        onNameChange = {},
        balanceAsString = "$0",
        onBalanceChange = {},
        cancel = {},
        save = {}
    )
}