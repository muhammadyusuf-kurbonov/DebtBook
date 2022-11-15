package uz.qmgroup.debtbook.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.qmgroup.debtbook.data.AppDatabase
import uz.qmgroup.debtbook.domain.models.Contact
import uz.qmgroup.debtbook.domain.repositories.AppRepository

class AppRepositoryImpl(private val database: AppDatabase) : AppRepository {
    override suspend fun saveNew(contact: Contact) {
        database.contactsDao.insert(contact.toDomainModel())
    }

    override fun findAllContacts(): Flow<List<Contact>> =
        database.contactsDao.getAllContacts().map { it.map { entity -> entity.toDomainModel() } }
}