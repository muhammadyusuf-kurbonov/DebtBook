package uz.qmgroup.debtbook.ui.components

import android.icu.text.NumberFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactItem(
    modifier: Modifier = Modifier,
    contactName: String,
    balance: Float,
    onClick: () -> Unit = {}
) {
    Card(modifier = modifier, shape = RoundedCornerShape(6.dp), onClick = onClick) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = contactName, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(end = 8.dp))

            Text(text = NumberFormat.getCurrencyInstance().format(balance), style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview
@Composable
fun ContactsItemPreview() {
    ContactItem(
        contactName = "Joe Martin",
        balance = 1.7f
    )
}
@Preview(showSystemUi = true)
@Composable
fun ContactsItemsListPreview() {
    LazyColumn {
        items(15) {
            ContactItem(
                modifier = Modifier.fillMaxSize().padding(4.dp, 1.dp),
                contactName = "Contact #${it}",
                balance = -5f + it * 2
            )
        }
    }
}