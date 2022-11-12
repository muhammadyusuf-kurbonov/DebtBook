package uz.qmgroup.debtbook.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.qmgroup.debtbook.domain.models.Contact

@Entity
data class ContactEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String = "",
    val balance: Float = 0f
) {
    fun toDomainModel(): Contact {
        return Contact(name, balance, id)
    }
}