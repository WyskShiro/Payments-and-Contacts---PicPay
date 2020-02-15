package will.shiro.domain.entity

import java.io.Serializable
import java.util.*

data class CreditCard(
    val id: Long = 0,
    val number: String = "",
    val ownerName: String = "",
    val expirationDate: Date = Date(),
    val cvv: Int = 0
) : Serializable