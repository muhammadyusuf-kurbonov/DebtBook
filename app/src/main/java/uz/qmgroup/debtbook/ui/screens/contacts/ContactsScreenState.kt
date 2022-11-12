package uz.qmgroup.debtbook.ui.screens.contacts

import uz.qmgroup.debtbook.domain.models.Contact

sealed class ContactsScreenState {
    object Loading: ContactsScreenState()
    class ContactsLoaded(val lists: List<Contact>): ContactsScreenState()
}
