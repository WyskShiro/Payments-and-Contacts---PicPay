package will.shiro.data.repository

import io.reactivex.Single
import will.shiro.data.api.client.ApiClient
import will.shiro.data.api.entity.ApiTransaction
import will.shiro.data.api.entity.ApiTransactionRequest
import will.shiro.data.api.entity.ApiTransactionResponse
import will.shiro.data.api.entity.ApiUser
import will.shiro.data.util.mapper.Mapper
import will.shiro.domain.boundary.UserRepository
import will.shiro.domain.entity.Transaction
import will.shiro.domain.entity.TransactionRequest
import will.shiro.domain.entity.User
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val apiUserMapper: Mapper<ApiUser, User>,
    private val transactionRequestMapper: Mapper<TransactionRequest, ApiTransactionRequest>,
    private val apiTransactionMapper: Mapper<ApiTransactionResponse, Transaction>
) : UserRepository {

    override fun getUsers(): Single<List<User>> {
        return apiClient.getUsers().map(apiUserMapper::transformList)
    }

    override fun createPayment(transactionRequest: TransactionRequest): Single<Transaction> {
        return apiClient.createTransaction(
            transactionRequestMapper.transform(transactionRequest)
        ).map(apiTransactionMapper::transform)
    }
}