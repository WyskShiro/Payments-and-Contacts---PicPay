package will.shiro.data.mapper

import will.shiro.data.entity.ApiUser
import will.shiro.data.util.mapper.Mapper
import will.shiro.domain.entity.User
import javax.inject.Inject

class ApiUserToUserMapper @Inject constructor() : Mapper<ApiUser, User>() {
    override fun transform(t: ApiUser): User {
        return User(
            id = t.id,
            name = t.name,
            img = t.img,
            username = t.username
        )
    }
}