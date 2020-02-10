package will.shiro.desafiopicpay.util.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import will.shiro.desafiopicpay.PicPayApplication
import will.shiro.desafiopicpay.util.di.module.ActivityBindingModule
import will.shiro.desafiopicpay.util.di.module.ApiProviderModule
import will.shiro.desafiopicpay.util.di.module.ApplicationBindingModule
import will.shiro.desafiopicpay.util.di.module.MapperModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationBindingModule::class,
        ApiProviderModule::class,
        ActivityBindingModule::class,
        MapperModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }

    fun inject(picPayApplication: PicPayApplication): PicPayApplication
}
