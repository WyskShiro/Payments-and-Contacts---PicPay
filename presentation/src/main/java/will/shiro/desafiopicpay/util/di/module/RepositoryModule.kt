package will.shiro.desafiopicpay.util.di.module

import dagger.Binds
import dagger.Module
import will.shiro.data.repository.DefaultCreditCardRepository
import will.shiro.data.repository.DefaultUserRepository
import will.shiro.domain.boundary.CreditCardRepository
import will.shiro.domain.boundary.UserRepository

@Module
interface RepositoryModule {

    @Binds
    fun bindUserRepository(repository: DefaultUserRepository): UserRepository

    @Binds
    fun bindCreditCardRepository(repository: DefaultCreditCardRepository): CreditCardRepository
}