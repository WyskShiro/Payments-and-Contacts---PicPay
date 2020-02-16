package will.shiro.desafiopicpay.util.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import will.shiro.desafiopicpay.util.base.BaseFragment
import will.shiro.desafiopicpay.util.di.scope.FragmentScope
import will.shiro.desafiopicpay.view.user.creditcard.create.CreateCreditCardFragment
import will.shiro.desafiopicpay.view.user.creditcard.create.PrimingCreditCardFragment
import will.shiro.desafiopicpay.view.user.creditcard.payment.PaymentCreditCardFragment
import will.shiro.desafiopicpay.view.user.list.ContactListFragment

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun contributesBaseFragment(): BaseFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun contributesContactListFragment(): ContactListFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun contributesPrimingCreditCardFragment(): PrimingCreditCardFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun contributesPaymentCreditCardFragment(): PaymentCreditCardFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun contributesCreateCreditCardFragment(): CreateCreditCardFragment
}