package will.shiro.desafiopicpay.util.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import will.shiro.desafiopicpay.util.base.BaseFragment
import will.shiro.desafiopicpay.util.di.scope.FragmentScope
import will.shiro.desafiopicpay.view.user.creditcard.create.CreateCreditCardFragment
import will.shiro.desafiopicpay.view.user.creditcard.create.PrimingCreditCardFragment
import will.shiro.desafiopicpay.view.user.creditcard.payment.PaymentCreditCardFragment
import will.shiro.desafiopicpay.view.user.creditcard.payment.PaymentCreditCardProvider
import will.shiro.desafiopicpay.view.user.creditcard.receipt.ReceiptCreditCardFragment
import will.shiro.desafiopicpay.view.user.list.ContactListFragment

@Module
abstract class FragmentBindingModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesBaseFragment(): BaseFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesContactListFragment(): ContactListFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesPrimingCreditCardFragment(): PrimingCreditCardFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [PaymentCreditCardProvider::class])
    abstract fun contributesPaymentCreditCardFragment(): PaymentCreditCardFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesCreateCreditCardFragment(): CreateCreditCardFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesReceiptCreditCardFragment(): ReceiptCreditCardFragment
}