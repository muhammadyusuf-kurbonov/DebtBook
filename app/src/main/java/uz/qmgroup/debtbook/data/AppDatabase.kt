package uz.qmgroup.debtbook.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ContactEntity::class], version = 2, autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "main.db")
                    .build()

            return instance!!
        }
    }

    abstract val contactsDao: ContactsDao
}