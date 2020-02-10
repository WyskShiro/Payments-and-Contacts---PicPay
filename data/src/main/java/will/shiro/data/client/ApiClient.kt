package will.shiro.data.client

import will.shiro.data.util.request.RequestHandler
import javax.inject.Inject

class ApiClient @Inject constructor(
    private val apiService: ApiService
) : RequestHandler()