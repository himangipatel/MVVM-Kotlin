package com.structure.kotlin.injection.module


import android.content.Context
import com.structure.kotlin.apiutills.ApiUtility
import com.structure.kotlin.network.RestApi
import com.structure.kotlin.utills.Constants
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED
import java.sql.Types.TIME
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {

    private val USER_NAME = "himangi"
    private val PASSWORD = "123456"

    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): RestApi {
        return retrofit.create(RestApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiUtility.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }


    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val httpClient = OkHttpClient().newBuilder()
        .connectTimeout(TIME.toLong(), TimeUnit.SECONDS)
        .readTimeout(TIME.toLong(), TimeUnit.SECONDS)
        .writeTimeout(TIME.toLong(), TimeUnit.SECONDS)
        .addInterceptor(logging)
        /*.addInterceptor(Interceptor { chain ->
            val original = chain.request()
            val basic = Credentials.basic(
                USER_NAME,
                PASSWORD
            )
          *//*  val requestBuilder = original.newBuilder().header("Authorization", basic)
            requestBuilder.header("Accept", "application/json")
            requestBuilder.method(original.method(), original.body())
            val request = requestBuilder.build()*//*
            val response = chain.proceed(request)
            if (response.isSuccessful) {
                return@Interceptor response.newBuilder()
                    .body(ResponseBody.create(response.body()!!.contentType(), response.body()!!.string()))
                    .build()
            } else {
                if (response.code() == HTTP_UNAUTHORIZED) {
                    //log out
                }
            }
            response
        })*/.build()

}