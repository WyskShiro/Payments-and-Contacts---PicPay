package will.shiro.domain.boundary

import io.reactivex.Single
import will.shiro.domain.entity.CreditCard

interface CreditCardRepository {

    fun getOne(): Single<CreditCard>
    fun addOrUpdate(creditCard: CreditCard): Single<CreditCard>
}