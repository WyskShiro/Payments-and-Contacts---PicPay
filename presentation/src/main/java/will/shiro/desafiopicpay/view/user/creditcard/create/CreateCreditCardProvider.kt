package will.shiro.desafiopicpay.view.user.creditcard.create

import dagger.Module
import dagger.Provides
import will.shiro.domain.entity.CreditCard
import javax.inject.Named

@Module
object CreateCreditCardProvider {

    @Provides
    @Named(NAMED_CREDIT_CARD)
    fun provideCreditCard(fragment: CreateCreditCardFragment): CreditCard? {
        return fragment.args.creditCard
    }

    const val NAMED_CREDIT_CARD = "NAMED_CREDIT_CARD"
}