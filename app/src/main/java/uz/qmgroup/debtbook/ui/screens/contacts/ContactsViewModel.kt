package uz.qmgroup.debtbook.ui.screens.contacts

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import uz.qmgroup.debtbook.data.AppDatabase

class ContactsViewModel : ViewModel() {
    private val _currentState = MutableStateFlow<ContactsScreenState>(ContactsScreenState.Loading)
    val currentState = _currentState.asStateFlow()

    var contactDialogShown by mutableStateOf(false)
        private set

    var contactIdForNewTransaction by mutableStateOf<Int?>(null)
        private set

    fun loadData(context: Context) {
        viewModelScope.launch {
            val dao = AppDatabase.getDatabase(context).contactsDao

            _currentState.emitAll(
                dao.getAllContacts().map {
                    ContactsScreenState.ContactsLoaded(
                        it.map { entity ->
                            entity.toDomainModel()
                        }
                    )
                }
            )
        }
    }

    fun showDialog() {
        contactDialogShown = true
    }

    fun hideDialog() {
        contactDialogShown = false
    }

    fun newTransactionFor(id: Int) {
        contactIdForNewTransaction = id
    }

    fun hideTransactionDialog() {
        contactIdForNewTransaction = null
    }
}