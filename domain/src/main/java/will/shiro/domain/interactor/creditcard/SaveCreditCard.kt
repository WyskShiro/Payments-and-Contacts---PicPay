package will.shiro.domain.interactor.creditcard

import io.reactivex.Single
import will.shiro.domain.boundary.CreditCardRepository
import will.shiro.domain.entity.CreditCard
import will.shiro.domain.util.form.CreditCardFormFields
import javax.inject.Inject

class SaveCreditCard @Inject constructor(
    private val creditCardRepository: CreditCardRepository
) {

    fun execute(creditCardFormFields: CreditCardFormFields): Single<CreditCard> {
        return creditCardRepository.addOrUpdate(
            creditCardFormFields.buildCreditCard()
        )
    }
}