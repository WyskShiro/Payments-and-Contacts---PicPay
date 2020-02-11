package will.shiro.domain.boundary

import io.reactivex.Single
import will.shiro.domain.entity.User

interface UserRepository {

    fun getUsers(): Single<List<User>>
}