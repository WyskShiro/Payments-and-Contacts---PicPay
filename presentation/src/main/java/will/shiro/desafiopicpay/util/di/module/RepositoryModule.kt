package will.shiro.desafiopicpay.util.di.module

import dagger.Binds
import dagger.Module
import will.shiro.data.repository.DefaultUserRepository
import will.shiro.domain.boundary.UserRepository

@Module
interface RepositoryModule {

    @Binds
    fun bindUserRepository(repository: DefaultUserRepository): UserRepository
}