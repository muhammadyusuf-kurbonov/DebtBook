package uz.qmgroup.debtbook.ui.screens.contacts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.qmgroup.debtbook.domain.models.Contact
import uz.qmgroup.debtbook.ui.components.ContactItem

@Composable
fun ContactsScreenContent(
    modifier: Modifier = Modifier,
    list: List<Contact>,
    newContactDialogRequest: () -> Unit,
    newTransactionDialogRequest: (id: Int) -> Unit,
) {
    Box(modifier = modifier) {
        if (list.isNotEmpty())
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(list) {
                    ContactItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 1.dp),
                        contactName = it.name,
                        balance = it.balance,
                        onClick = { newTransactionDialogRequest(it.id!!) }
                    )
                }
            }
        else
            CompositionLocalProvider(
                LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.38f
                )
            ) {
                Text(
                    text = "No data yet.\nLet's start",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal
                )
            }

        FloatingActionButton(
            onClick = newContactDialogRequest,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add new contact")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ContactsScreenContentPreview() {
    ContactsScreenContent(
        list = listOf(
            Contact("Joe Manatry", balance = -5f),
            Contact("Mariarty Kalana", balance = 14f),
            Contact("Laomanu", balance = 0f)
        ),
        newContactDialogRequest = {},
        newTransactionDialogRequest = {}
    )
}

@Preview(showSystemUi = true)
@Composable
fun ContactsScreenContentEmptyPreview() {
    ContactsScreenContent(
        list = listOf(),
        newContactDialogRequest = {},
        newTransactionDialogRequest = {}
    )
}