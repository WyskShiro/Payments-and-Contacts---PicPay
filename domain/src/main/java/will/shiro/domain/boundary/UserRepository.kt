package will.shiro.domain.boundary

import io.reactivex.Single
import will.shiro.domain.entity.Transaction
import will.shiro.domain.entity.TransactionRequest
import will.shiro.domain.entity.User

interface UserRepository {

    fun getUsers(): Single<List<User>>

    fun createPayment(transactionRequest: TransactionRequest): Single<Transaction>
}