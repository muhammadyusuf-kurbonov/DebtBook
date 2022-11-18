package uz.qmgroup.debtbook.ui.screens.transactionDialog

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
fun TransactionDialog(
    modifier: Modifier = Modifier,
    id: Int,
    dismiss: () -> Unit,
    viewModel: TransactionDialogViewModel = viewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    var balance by remember { mutableStateOf(0f) }

    LaunchedEffect(key1 = id) {
        viewModel.init(context, id)
        balance = 0f
    }

    when (val currentState = state) {
        is TransactionDialogState.PatchPending, is TransactionDialogState.DataInput -> {
            Dialog(onDismissRequest = dismiss) {
                TransactionDialogContent(
                    modifier = modifier,
                    contact = currentState.contact,
                    balanceAsString = NumberFormat.getCurrencyInstance().format(balance),
                    onBalanceChange = {
                        balance =
                            NumberFormat.getCurrencyInstance().parseOrNull(it)?.toFloat() ?: 0f
                    },
                    cancel = dismiss,
                    save = { add -> viewModel.save(currentState.contact!!, balance, add) },
                    saveDisabled = state is TransactionDialogState.PatchPending
                )
            }
        }

        is TransactionDialogState.PatchCompleted -> {
            val hostState = LocalSnackbarHostState.current

            LaunchedEffect(key1 = Unit) {
                hostState.showSnackbar("Transaction saved!")
                dismiss()
            }
        }
    }
}