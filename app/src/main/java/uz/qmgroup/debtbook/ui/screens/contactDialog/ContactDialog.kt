package uz.qmgroup.debtbook.ui.screens.contactDialog

import android.icu.text.NumberFormat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import uz.qmgroup.debtbook.parseOrNull
import uz.qmgroup.debtbook.ui.providers.LocalSnackbarHostState

@Composable
fun ContactDialog(
    modifier: Modifier = Modifier,
    dismiss: () -> Unit,
    viewModel: ContactDialogViewModel = viewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    var name by remember { mutableStateOf("") }
    var balance by remember { mutableStateOf(0f) }

    LaunchedEffect(key1 = Unit) {
        viewModel.init()
    }

    when (state) {
        ContactDialogState.SavePending, ContactDialogState.DataInput -> {
            Dialog(onDismissRequest = dismiss) {
                ContactDialogContent(
                    modifier = modifier,
                    name = name,
                    onNameChange = { name = it },
                    balanceAsString = NumberFormat.getCurrencyInstance().format(balance),
                    onBalanceChange = {
                        balance = NumberFormat.getCurrencyInstance().parseOrNull(it)?.toFloat() ?: 0f
                    },
                    cancel = dismiss,
                    save = { viewModel.save(context, name, balance) },
                    saveDisabled = state is ContactDialogState.SavePending
                )
            }
        }

        ContactDialogState.SaveCompleted -> {
            val hostState = LocalSnackbarHostState.current

            LaunchedEffect(key1 = Unit) {
                hostState.showSnackbar("Contact saved!")
                dismiss()
            }
        }
    }
}