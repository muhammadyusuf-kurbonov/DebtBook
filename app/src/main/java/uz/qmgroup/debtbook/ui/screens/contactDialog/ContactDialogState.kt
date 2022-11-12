package uz.qmgroup.debtbook.ui.screens.contactDialog

sealed class ContactDialogState {
    object DataInput: ContactDialogState()
    object SavePending: ContactDialogState()
    object SaveCompleted: ContactDialogState()
}
