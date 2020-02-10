package will.shiro.desafiopicpay.util.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import will.shiro.desafiopicpay.util.base.BaseFragment
import will.shiro.desafiopicpay.util.di.scope.FragmentScope
import will.shiro.desafiopicpay.view.MainFragment

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun contributesBaseFragment(): BaseFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun contributesMainFragment(): MainFragment
}