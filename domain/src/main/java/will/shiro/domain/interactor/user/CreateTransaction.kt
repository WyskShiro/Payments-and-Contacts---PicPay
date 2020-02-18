package will.shiro.domain.interactor.user

import io.reactivex.Single
import will.shiro.domain.boundary.UserRepository
import will.shiro.domain.entity.Transaction
import will.shiro.domain.entity.TransactionRequest
import will.shiro.domain.util.throwable.TransactionRejectedThrowable
import javax.inject.Inject

class CreateTransaction @Inject constructor(
    private val userRepository: UserRepository
) {

    fun execute(transactionRequest: TransactionRequest): Single<Transaction> {
        return userRepository.createPayment(transactionRequest).flatMap(::handleTransactionSuccess)
    }

    private fun handleTransactionSuccess(transaction: Transaction): Single<Transaction> {
        return if (transaction.success) {
            Single.just(transaction)
        } else {
            Single.error(TransactionRejectedThrowable())
        }
    }
}