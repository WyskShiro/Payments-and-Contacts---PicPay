package will.shiro.data.client

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import will.shiro.data.entity.ApiUser

interface ApiService {

    @GET("users")
    fun getUsers(): Single<Response<List<ApiUser>>>
}