package will.shiro.domain.interactor.user

import io.reactivex.Single
import will.shiro.domain.boundary.UserRepository
import will.shiro.domain.entity.Transaction
import will.shiro.domain.entity.TransactionRequest
import javax.inject.Inject

class CreateTransaction @Inject constructor(
    private val userRepository: UserRepository
) {

    fun execute(transactionRequest: TransactionRequest): Single<Transaction> {
        return userRepository.createPayment(transactionRequest)
    }
}