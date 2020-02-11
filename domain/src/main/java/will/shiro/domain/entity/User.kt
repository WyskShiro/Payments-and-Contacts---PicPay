package will.shiro.domain.entity

import java.io.Serializable

data class User(
    val id: Long,
    val name: String,
    val img: String,
    val username: String
) : Serializable