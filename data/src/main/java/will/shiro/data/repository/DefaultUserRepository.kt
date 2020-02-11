package will.shiro.data.repository

import io.reactivex.Single
import will.shiro.data.client.ApiClient
import will.shiro.data.entity.ApiUser
import will.shiro.data.util.mapper.Mapper
import will.shiro.domain.boundary.UserRepository
import will.shiro.domain.entity.User
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val apiUserMapper: Mapper<ApiUser, User>
) : UserRepository {
    override fun getUsers(): Single<List<User>> {
        return apiClient.getUsers().map(apiUserMapper::transformList)
    }
}