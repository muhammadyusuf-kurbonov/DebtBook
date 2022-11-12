package uz.qmgroup.debtbook.domain.models

data class Contact(
    val name: String,
    val balance: Float,
    val id: Int? = null
)