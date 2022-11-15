package uz.qmgroup.debtbook.domain.repositories

import android.content.Context
import uz.qmgroup.debtbook.data.AppDatabase
import uz.qmgroup.debtbook.data.repository.AppRepositoryImpl

object RepositoryStore {
    lateinit var repository: AppRepository

    fun initializeRepositories(context: Context) {
        repository = AppRepositoryImpl(AppDatabase.getDatabase(context))
    }
}