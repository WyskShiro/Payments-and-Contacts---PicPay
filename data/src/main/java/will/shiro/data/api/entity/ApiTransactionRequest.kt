package will.shiro.data.api.entity

import com.google.gson.annotations.SerializedName

data class ApiTransactionRequest(
    @SerializedName("card_number") val cardNumber: String,
    @SerializedName("cvv") val cvv: Int,
    @SerializedName("value") val value: Float,
    @SerializedName("expiry_date") val expiryDate: String,
    @SerializedName("destination_user_id") val destinationUserId: Long
)