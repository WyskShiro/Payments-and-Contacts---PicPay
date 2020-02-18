package will.shiro.desafiopicpay.util.di.module

import dagger.Binds
import dagger.Module
import will.shiro.desafiopicpay.util.logger.AndroidLogger
import will.shiro.desafiopicpay.util.resources.AndroidStrings
import will.shiro.domain.util.Strings
import will.shiro.domain.util.logger.Logger

@Module
interface ApplicationBindingModule {

    @Binds
    fun bindLogger(logger: AndroidLogger): Logger

    @Binds
    fun bindStrings(strings: AndroidStrings): Strings
}