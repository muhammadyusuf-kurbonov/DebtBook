package uz.qmgroup.debtbook.ui.screens.transactionDialog

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.qmgroup.debtbook.data.AppDatabase
import uz.qmgroup.debtbook.domain.models.Contact
import uz.qmgroup.debtbook.domain.models.Transaction
import uz.qmgroup.debtbook.domain.repositories.RepositoryStore

class TransactionDialogViewModel : ViewModel() {
    private val _state = MutableStateFlow<TransactionDialogState>(TransactionDialogState.DataInput(null))
    val state = _state.asStateFlow()

    fun save(contact: Contact, amount: Float, plus: Boolean) {
        _state.update { TransactionDialogState.PatchPending(it.contact) }
        viewModelScope.launch {
            val delta = if (plus) amount else -amount
            val repository = RepositoryStore.repository
            repository.saveNew(Transaction(contact, delta))
            _state.update { TransactionDialogState.PatchCompleted(it.contact) }
        }
    }

    fun init(context: Context, id: Int) {
        _state.tryEmit(TransactionDialogState.DataInput(contact = null))
        viewModelScope.launch {
            val dao = AppDatabase.getDatabase(context).contactsDao
            val contact = dao.getContact(id)
            _state.tryEmit(TransactionDialogState.DataInput(contact = contact.toDomainModel()))
        }
    }
}