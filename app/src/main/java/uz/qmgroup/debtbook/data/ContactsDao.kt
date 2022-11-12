package uz.qmgroup.debtbook.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {
    @Insert
    suspend fun insert(newContact: ContactEntity)

    @Query("SELECT * FROM ContactEntity")
    fun getAllContacts(): Flow<List<ContactEntity>>

    @Query("UPDATE ContactEntity SET balance = :newBalance WHERE id = :id")
    suspend fun updateBalanceFor(id: Int, newBalance: Float)

    @Query("SELECT * FROM ContactEntity WHERE id = :id")
    suspend fun getContact(id: Int): ContactEntity
}