package uz.qmgroup.debtbook.domain.repositories

import kotlinx.coroutines.flow.Flow
import uz.qmgroup.debtbook.domain.models.Contact

interface ContactsRepository {
    suspend fun saveNew(contact: Contact)

    fun findAllContacts(): Flow<List<Contact>>
}