package will.shiro.data.api.mapper

import will.shiro.data.api.entity.ApiTransactionResponse
import will.shiro.data.api.entity.ApiUser
import will.shiro.data.util.mapper.Mapper
import will.shiro.domain.entity.Transaction
import will.shiro.domain.entity.User
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiTransactionResponseToTransactionMapper @Inject constructor(
    private val apiUserMapper: Mapper<ApiUser, User>
) : Mapper<ApiTransactionResponse, Transaction>() {

    override fun transform(t: ApiTransactionResponse): Transaction {
        return Transaction(
            id = t.transaction.id,
            timestamp = Date(TimeUnit.SECONDS.toMillis(t.transaction.timestamp)),
            value = t.transaction.value,
            destinationUser = apiUserMapper.transform(t.transaction.destinationUser),
            success = t.transaction.success,
            status = t.transaction.status
        )
    }
}