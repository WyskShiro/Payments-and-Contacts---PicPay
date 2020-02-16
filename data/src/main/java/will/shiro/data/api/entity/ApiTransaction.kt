package will.shiro.data.api.entity

import com.google.gson.annotations.SerializedName

data class ApiTransactionResponse(
    @SerializedName("transaction") val transaction: ApiTransaction
)

data class ApiTransaction(
    @SerializedName("id") val id: Long,
    @SerializedName("timestamp") val timestamp: Long,
    @SerializedName("value") val value: Float,
    @SerializedName("destination_user") val destinationUser: ApiUser,
    @SerializedName("success") val success: Boolean,
    @SerializedName("status") val status: String
)