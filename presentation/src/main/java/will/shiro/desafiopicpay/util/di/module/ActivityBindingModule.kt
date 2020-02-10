package will.shiro.desafiopicpay.util.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import will.shiro.desafiopicpay.util.di.scope.ActivityScope
import will.shiro.desafiopicpay.view.MainActivity

@Module
interface ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    fun contributeMainActivity(): MainActivity
}