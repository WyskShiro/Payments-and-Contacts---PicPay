package will.shiro.data.api.client

import io.reactivex.Single
import will.shiro.data.api.entity.ApiTransactionRequest
import will.shiro.data.api.entity.ApiTransactionResponse
import will.shiro.data.api.entity.ApiUser
import will.shiro.data.util.request.RequestHandler
import javax.inject.Inject

class ApiClient @Inject constructor(
    private val apiService: ApiService
) : RequestHandler() {

    fun getUsers(): Single<List<ApiUser>> {
        return makeRequest(apiService.getUsers())
    }

    fun createTransaction(transactionRequest: ApiTransactionRequest): Single<ApiTransactionResponse> {
        return makeRequest(apiService.createTransaction(transactionRequest))
    }
}