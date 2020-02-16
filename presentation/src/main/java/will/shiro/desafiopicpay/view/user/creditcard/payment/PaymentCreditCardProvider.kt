package will.shiro.desafiopicpay.view.user.creditcard.payment

import dagger.Module
import dagger.Provides
import will.shiro.domain.entity.CreditCard
import will.shiro.domain.entity.User
import javax.inject.Named

@Module
object PaymentCreditCardProvider {

    @Provides
    @Named(NAMED_USER)
    fun provideUser(fragment: PaymentCreditCardFragment): User {
        return fragment.args.user
    }

    @Provides
    @Named(NAMED_CREDIT_CARD)
    fun provideCreditCard(fragment: PaymentCreditCardFragment): CreditCard {
        return fragment.args.creditCard
    }

    const val NAMED_USER = "NAMED_USER"
    const val NAMED_CREDIT_CARD = "NAMED_CREDIT_CARD"
}