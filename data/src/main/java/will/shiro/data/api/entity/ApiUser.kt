package will.shiro.data.api.entity

import com.google.gson.annotations.SerializedName

data class ApiUser(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("img") val img: String?,
    @SerializedName("username") val username: String
)