package will.shiro.desafiopicpay.util.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import will.shiro.desafiopicpay.view.MainActivity
import will.shiro.desafiopicpay.util.di.scope.ActivityScope

@Module
interface ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity
}