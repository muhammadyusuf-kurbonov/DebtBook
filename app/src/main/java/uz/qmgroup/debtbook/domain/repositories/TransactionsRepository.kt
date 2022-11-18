package uz.qmgroup.debtbook.domain.repositories

import uz.qmgroup.debtbook.domain.models.Transaction

interface TransactionsRepository {
    suspend fun saveNew(transaction: Transaction)
}