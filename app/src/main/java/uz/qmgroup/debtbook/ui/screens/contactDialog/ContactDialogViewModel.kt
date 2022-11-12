package uz.qmgroup.debtbook.ui.screens.contactDialog

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.qmgroup.debtbook.data.AppDatabase
import uz.qmgroup.debtbook.data.ContactEntity

class ContactDialogViewModel : ViewModel() {
    private val _state = MutableStateFlow<ContactDialogState>(ContactDialogState.DataInput)
    val state = _state.asStateFlow()

    fun save(context: Context, name: String, balance: Float) {
        _state.tryEmit(ContactDialogState.SavePending)
        viewModelScope.launch {
            val dao = AppDatabase.getDatabase(context).contactsDao
            dao.insert(ContactEntity(name = name, balance = balance))
            _state.tryEmit(ContactDialogState.SaveCompleted)
        }
    }

    fun init() {
        _state.tryEmit(ContactDialogState.DataInput)
    }
}