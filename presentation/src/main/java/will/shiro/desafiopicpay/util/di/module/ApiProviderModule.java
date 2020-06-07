package will.shiro.desafiopicpay.util.di.module;

import java.util.Set;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import will.shiro.data.api.client.ApiClient;
import will.shiro.data.api.client.ApiService;
import will.shiro.desafiopicpay.BuildConfig;

@Module
public class ApiProviderModule {

    @Provides
    @IntoSet
    public Interceptor provideHttpLoggingInterceptor(HttpLoggingInterceptor.Level logLevel) {
        return new HttpLoggingInterceptor().setLevel(logLevel);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Set<Interceptor> interceptors) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        for (Interceptor interceptor : interceptors) {
            okHttpClientBuilder.addInterceptor(interceptor);
        }
        return okHttpClientBuilder.build();
    }

    @Provides
    @Singleton
    public RxJava2CallAdapterFactory provideRxJavaCallAdapter() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        Retrofit.Builder builder = new Retrofit.Builder();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        return builder
                .client(okHttpClient)
                .baseUrl(BuildConfig.API_URL)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    public ApiClient provideApiClient(ApiService apiService) {
        return new ApiClient(apiService);
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor.Level provideLogLevel() {
        if (BuildConfig.DEBUG) {
            return HttpLoggingInterceptor.Level.BODY;
        }
        return HttpLoggingInterceptor.Level.NONE;
    }
}
