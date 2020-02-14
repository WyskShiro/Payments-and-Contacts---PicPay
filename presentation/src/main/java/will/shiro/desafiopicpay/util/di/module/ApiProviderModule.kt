package will.shiro.desafiopicpay.util.di.module

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import will.shiro.data.client.ApiClient
import will.shiro.data.client.ApiService
import will.shiro.desafiopicpay.BuildConfig
import javax.inject.Singleton

@Module
object ApiProviderModule {

    @Provides
    @IntoSet
    fun provideHttpLoggingInterceptor(logLevel: HttpLoggingInterceptor.Level): Interceptor {
        return HttpLoggingInterceptor().setLevel(logLevel)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        for (interceptor in interceptors) {
            okHttpClientBuilder.addInterceptor(interceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRxJavaCallAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        rxJavaCallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.API_URL)
            .addCallAdapterFactory(rxJavaCallAdapterFactory)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiClient(apiService: ApiService): ApiClient {
        return ApiClient(apiService)
    }

    @Provides
    @Singleton
    fun provideLogLevel(): HttpLoggingInterceptor.Level {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}