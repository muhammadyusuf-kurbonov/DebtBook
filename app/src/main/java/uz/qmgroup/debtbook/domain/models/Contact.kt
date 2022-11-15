package uz.qmgroup.debtbook.domain.models

import uz.qmgroup.debtbook.data.ContactEntity

data class Contact(
    val name: String,
    val balance: Float,
    val id: Int? = null
) {
    fun toDomainModel(): ContactEntity {
        return ContactEntity(
            id ?: 0,
            name = name,
            balance = balance
        )
    }
}