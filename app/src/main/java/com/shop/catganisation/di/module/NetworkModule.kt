package com.shop.catganisation.di.module

import com.shop.catganisation.BuildConfig
import com.shop.catganisation.network.CatsApi
import com.shop.catganisation.utils.MockInterceptor
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Ovidiu Florin Luca on 07/10/2020.
 */
@Module
object NetworkModule {
    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideCatsApi(retrofit: Retrofit): CatsApi {
        return retrofit.create(CatsApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
//        val authInterceptor = AuthInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        val client = OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .addInterceptor(authInterceptor)
//            .build()


        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(loggingInterceptor)
        httpClient.addInterceptor(MockInterceptor())
//        httpClient.addInterceptor(object : Interceptor {
//            @Throws(IOException::class)
//            override fun intercept(chain: Interceptor.Chain): Response {
//                val original: Request = chain.request()
//
//                // Request customization: add request headers
//                val requestBuilder: Request.Builder = original.newBuilder()
//                    .addHeader("x-api-key", "e51009b2-ce0a-471b-bf44-1f97ab68be0c")
//                val request: Request = requestBuilder.build()
//                return chain.proceed(request)
//            }
//        })

        val client = httpClient.build()


        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

/*    class AuthInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response = chain.run {
            proceed(
                request()
                    .newBuilder()
                    .header("x-api-key", "e51009b2-ce0a-471b-bf44-1f97ab68be0c")
                    .build()
            )
        }
    }*/
}