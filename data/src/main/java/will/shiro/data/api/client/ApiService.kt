package will.shiro.data.api.client

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import will.shiro.data.api.entity.ApiTransactionRequest
import will.shiro.data.api.entity.ApiTransactionResponse
import will.shiro.data.api.entity.ApiUser

interface ApiService {

    @GET("users")
    fun getUsers(): Single<Response<List<ApiUser>>>

    @POST("transaction")
    fun createTransaction(
        @Body apiTransactionRequest: ApiTransactionRequest
    ): Single<Response<ApiTransactionResponse>>
}