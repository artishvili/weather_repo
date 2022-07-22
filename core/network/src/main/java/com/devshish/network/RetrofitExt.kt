package com.devshish.network

import java.util.concurrent.TimeUnit
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit

private const val DEFAULT_TIMEOUT_VALUE = 5L

/**
 * Create [OkHttpClient] with default Logging Interceptor
 */
fun createOkHttpClient(
    interceptor: Interceptor
): OkHttpClient {
    val logging = HttpLoggingInterceptor().also {
        it.setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addNetworkInterceptor(logging)
        .connectTimeout(DEFAULT_TIMEOUT_VALUE, TimeUnit.MINUTES)
        .callTimeout(DEFAULT_TIMEOUT_VALUE, TimeUnit.MINUTES)
        .readTimeout(DEFAULT_TIMEOUT_VALUE, TimeUnit.MINUTES)
        .writeTimeout(DEFAULT_TIMEOUT_VALUE, TimeUnit.MINUTES)
        .build()
}

/**
 * Create [Retrofit] for REST network calls
 */
fun createRetrofit(
    baseUrl: String,
    client: OkHttpClient,
    converterFactory: Converter.Factory
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(converterFactory)
        .build()
}

/**
 * Create API class
 */
fun <T> createRetrofitService(retrofit: Retrofit, clazz: Class<T>): T {
    return retrofit.create(clazz)
}
