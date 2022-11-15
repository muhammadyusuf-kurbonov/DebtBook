package uz.qmgroup.debtbook.ui.screens.contactDialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.qmgroup.debtbook.domain.models.Contact
import uz.qmgroup.debtbook.domain.repositories.RepositoryStore

class ContactDialogViewModel : ViewModel() {
    private val _state = MutableStateFlow<ContactDialogState>(ContactDialogState.DataInput)
    val state = _state.asStateFlow()

    fun save(name: String, balance: Float) {
        _state.tryEmit(ContactDialogState.SavePending)
        viewModelScope.launch {
            RepositoryStore.repository.saveNew(Contact(name = name, balance = balance))
            _state.tryEmit(ContactDialogState.SaveCompleted)
        }
    }

    fun init() {
        _state.tryEmit(ContactDialogState.DataInput)
    }
}