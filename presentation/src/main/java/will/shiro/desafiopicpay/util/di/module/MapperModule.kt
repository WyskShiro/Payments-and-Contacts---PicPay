package will.shiro.desafiopicpay.util.di.module

import dagger.Binds
import dagger.Module
import will.shiro.data.api.entity.ApiUser
import will.shiro.data.api.mapper.ApiUserToUserMapper
import will.shiro.data.util.mapper.Mapper
import will.shiro.domain.entity.User

@Module
interface MapperModule {

    @Binds
    fun bindApiUserToUserMapper(apiUserToUserMapper: ApiUserToUserMapper): Mapper<ApiUser, User>
}