package will.shiro.domain.entity

import java.io.Serializable
import java.util.*

data class Transaction(
    val id: Long = 0,
    val timestamp: Date = Date(),
    val value: Float = 0f,
    val destinationUser: User,
    val success: Boolean = false,
    val status: String = ""
) : Serializable