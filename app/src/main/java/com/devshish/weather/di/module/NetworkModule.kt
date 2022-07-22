package com.devshish.weather.di.module

import com.devshish.network.createJsonConverterFactory
import com.devshish.network.createOkHttpClient
import com.devshish.network.createRetrofit
import com.devshish.weather.BuildConfig
import com.devshish.weather.di.component.AppScope
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

@Module(
    includes = [InterceptorsModule::class]
)
internal class NetworkModule {

    /**
     * Rest API
     */
    @[Provides AppScope]
    fun provideConverterFactory(): Converter.Factory = createJsonConverterFactory()

    @[Provides AppScope]
    fun provideOkHttp(
        @InterceptorsModule.ApiKeyInterceptor interceptor: Interceptor
    ): OkHttpClient = createOkHttpClient(interceptor)

    @[Provides AppScope]
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit = createRetrofit(BuildConfig.BASE_URL, okHttpClient, converterFactory)
}

@Module
internal class InterceptorsModule {

    @Qualifier
    annotation class ApiKeyInterceptor

    @[Provides AppScope ApiKeyInterceptor]
    fun provideApiKeyInterceptor(): Interceptor {
        return Interceptor {
            val url = it.request().url
                .newBuilder()
                .addQueryParameter("key", BuildConfig.API_KEY)
                .build()

            val request = it.request().newBuilder()
                .url(url)
                .build()

            it.proceed(request)
        }
    }
}
