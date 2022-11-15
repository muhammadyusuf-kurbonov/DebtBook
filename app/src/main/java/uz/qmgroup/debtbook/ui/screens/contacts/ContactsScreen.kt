package uz.qmgroup.debtbook.ui.screens.contacts

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import uz.qmgroup.debtbook.ui.components.LoadingScreen
import uz.qmgroup.debtbook.ui.screens.contactDialog.ContactDialog
import uz.qmgroup.debtbook.ui.screens.transactionDialog.TransactionDialog

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ContactsScreen(modifier: Modifier = Modifier, viewModel: ContactsViewModel = viewModel()) {
    val currentState by viewModel.currentState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit)  {
        viewModel.loadData()
    }

    AnimatedContent(targetState = currentState::class.simpleName) {
        when (val state = currentState) {
            is ContactsScreenState.ContactsLoaded -> {
                ContactsScreenContent(
                    modifier = modifier,
                    list = state.lists,
                    newContactDialogRequest = viewModel::showDialog,
                    newTransactionDialogRequest = viewModel::newTransactionFor
                )
            }

            ContactsScreenState.Loading -> {
                LoadingScreen(
                    modifier = modifier
                )
            }
        }
    }

    if (viewModel.contactDialogShown)
        ContactDialog(dismiss = viewModel::hideDialog)

    val contactIdForNewTransaction = viewModel.contactIdForNewTransaction

    if (contactIdForNewTransaction !== null)
        TransactionDialog(
            id = contactIdForNewTransaction,
            dismiss = viewModel::hideTransactionDialog
        )
}