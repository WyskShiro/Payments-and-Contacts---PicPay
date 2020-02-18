package will.shiro.data.util.request

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleTransformer
import retrofit2.Response

open class RequestHandler {
    protected fun <T> makeRequest(request: Single<Response<T>>): Single<T> {
        return request.compose(verifyResponseException())
            .compose(verifyRequestException())
            .compose(unwrap())
    }

    private fun <T> unwrap(): SingleTransformer<Response<T>, T> {
        return SingleTransformer { upstream ->
            upstream.map(Response<T>::body)
        }
    }

    private fun <T> verifyRequestException(): SingleTransformer<Response<T>, Response<T>> {
        return SingleTransformer { upstream ->
            upstream.onErrorResumeNext { t ->
                Single.error(Throwable(t))
            }
        }
    }

    private fun <T> verifyResponseException(): SingleTransformer<Response<T>, Response<T>> {
        return SingleTransformer { upstream ->
            upstream.doOnSuccess { response ->
                if (!response.isSuccessful) {
                    throw Exception("Response code: ${response.code()} \n Error body: ${response.errorBody()}")
                }
            }
        }
    }
}