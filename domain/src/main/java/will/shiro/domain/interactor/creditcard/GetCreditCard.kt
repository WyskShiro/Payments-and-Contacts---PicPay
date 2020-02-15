package will.shiro.domain.interactor.creditcard

import io.reactivex.Single
import will.shiro.domain.boundary.CreditCardRepository
import will.shiro.domain.entity.CreditCard
import javax.inject.Inject

class GetCreditCard @Inject constructor(
    private val creditCardRepository: CreditCardRepository
) {

    fun execute(): Single<CreditCard> {
        return creditCardRepository.getOne()
    }
}