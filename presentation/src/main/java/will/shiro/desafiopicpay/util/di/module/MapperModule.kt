package will.shiro.desafiopicpay.util.di.module

import dagger.Binds
import dagger.Module
import will.shiro.data.api.entity.ApiTransactionRequest
import will.shiro.data.api.entity.ApiTransactionResponse
import will.shiro.data.api.entity.ApiUser
import will.shiro.data.api.mapper.ApiTransactionResponseToTransactionMapper
import will.shiro.data.api.mapper.ApiUserToUserMapper
import will.shiro.data.api.mapper.TransactionRequestToTransactionMapper
import will.shiro.data.local.entity.RealmCreditCard
import will.shiro.data.local.mapper.CreditCardToRealmCreditCardMapper
import will.shiro.data.local.mapper.RealmCreditCardToCreditCardMapper
import will.shiro.data.util.mapper.Mapper
import will.shiro.domain.entity.CreditCard
import will.shiro.domain.entity.Transaction
import will.shiro.domain.entity.TransactionRequest
import will.shiro.domain.entity.User

@Module
interface MapperModule {

    @Binds
    fun bindApiUserToUserMapper(apiUserToUserMapper: ApiUserToUserMapper): Mapper<ApiUser, User>

    @Binds
    fun bindRealmCreditCardToCreditCardMapper(
        realmCreditCardToCreditCardMapper: RealmCreditCardToCreditCardMapper
    ): Mapper<RealmCreditCard, CreditCard>

    @Binds
    fun bindCreditCardToRealmCreditCardMapper(
        creditCardToRealmCreditCardMapper: CreditCardToRealmCreditCardMapper
    ): Mapper<CreditCard, RealmCreditCard>

    @Binds
    fun bindApiTransactionToTransaction(
        creditCardResponseToRealmCreditCardMapperMapper: ApiTransactionResponseToTransactionMapper
    ): Mapper<ApiTransactionResponse, Transaction>

    @Binds
    fun bindTransactionRequestToTransactionMapper(
        transactionRequestToTransactionMapper: TransactionRequestToTransactionMapper
    ): Mapper<TransactionRequest, ApiTransactionRequest>
}