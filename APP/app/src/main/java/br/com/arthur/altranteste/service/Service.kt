package br.com.arthur.altranteste.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Service {

    companion object {

        private const val maxSecondsToRequest: Long = 5

        private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        private val httpClient = OkHttpClient.Builder()
            .readTimeout(maxSecondsToRequest, TimeUnit.SECONDS)
            .connectTimeout(maxSecondsToRequest, TimeUnit.SECONDS)
            .writeTimeout(maxSecondsToRequest, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request()
                val builder = request.newBuilder()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                chain.proceed(builder.build())
            }.addInterceptor(loggingInterceptor)
            .build()

        private val gson = GsonBuilder()
            .setLenient()
            .create()

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://bank-app-test.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()

        fun getService(): API = retrofit.create(API::class.java)


    }

}