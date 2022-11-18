package uz.qmgroup.debtbook.domain.models

import java.time.LocalDateTime

data class Transaction(
    val contractor: Contact,
    val amount: Float,
    val note: String? = null,
    val dateTime: LocalDateTime = LocalDateTime.now()
)
