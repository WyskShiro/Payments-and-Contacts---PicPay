package will.shiro.domain.interactor.user

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import will.shiro.domain.boundary.UserRepository
import will.shiro.domain.entity.User

class GetUsersTest {

    @Test
    fun `get users returns a list`() {
        val mockedUserRepository = mockk<UserRepository>()
        val getUsers = GetUsers(mockedUserRepository)
        val mockedUsersList = mockk<List<User>>()
        every { mockedUserRepository.getUsers() } returns Single.just(mockedUsersList)

        assertEquals(
            getUsers.execute().blockingGet(),
            mockedUsersList
        )
    }
}