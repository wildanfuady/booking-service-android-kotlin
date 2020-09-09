package com.wildanfuady.bookingservice.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkConfig {

    fun getInterceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()
    }

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2/booking_service/")
                // localhost/booking_service
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getServiceRegister(): RegisterService = getRetrofit().create(RegisterService::class.java)
    fun getServiceLogin(): LoginService = getRetrofit().create(LoginService::class.java)

}