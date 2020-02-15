package will.shiro.data.api.client

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import will.shiro.data.api.entity.ApiUser

interface ApiService {

    @GET("users")
    fun getUsers(): Single<Response<List<ApiUser>>>
}