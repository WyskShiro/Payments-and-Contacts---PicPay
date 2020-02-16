package will.shiro.domain.entity

data class TransactionRequest(
    val cardNumber: String,
    val cvv: Int,
    val value: Float,
    val expiryDate: String,
    val destinationUserId: Long
)