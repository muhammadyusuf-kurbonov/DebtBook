package uz.qmgroup.debtbook.ui.screens.transactionDialog

import uz.qmgroup.debtbook.domain.models.Contact

sealed class TransactionDialogState(val contact: Contact?) {
    class DataInput(contact: Contact?): TransactionDialogState(contact)
    class PatchPending(contact: Contact?) : TransactionDialogState(contact)
    class PatchCompleted(contact: Contact?) : TransactionDialogState(contact)
}
