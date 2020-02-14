package will.shiro.domain.interactor.user

import io.reactivex.Single
import will.shiro.domain.boundary.UserRepository
import will.shiro.domain.entity.User
import javax.inject.Inject

class GetUsers @Inject constructor(
    private val userRepository: UserRepository
) {

    fun execute(): Single<List<User>> {
        return userRepository.getUsers()
    }
}