package will.shiro.data.client

import io.reactivex.Single
import will.shiro.data.entity.ApiUser
import will.shiro.data.util.request.RequestHandler
import javax.inject.Inject

class ApiClient @Inject constructor(
    private val apiService: ApiService
) : RequestHandler() {

    fun getUsers(): Single<List<ApiUser>> {
        return makeRequest(apiService.getUsers())
    }
}