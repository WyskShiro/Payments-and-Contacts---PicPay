package will.shiro.domain.entity

import java.io.Serializable

data class User(
    val id: Long = 0,
    val name: String = "",
    val img: String = "",
    val username: String = ""
) : Serializable