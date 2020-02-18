package will.shiro.data.api.mapper

import will.shiro.data.api.entity.ApiTransactionRequest
import will.shiro.data.util.mapper.Mapper
import will.shiro.domain.entity.TransactionRequest
import javax.inject.Inject

class TransactionRequestToTransactionMapper @Inject constructor() :
    Mapper<TransactionRequest, ApiTransactionRequest>() {

    override fun transform(t: TransactionRequest): ApiTransactionRequest {
        return ApiTransactionRequest(
            cardNumber = t.cardNumber,
            cvv = t.cvv,
            expiryDate = t.expiryDate,
            value = t.value,
            destinationUserId = t.destinationUserId
        )
    }
}