package com.kim344.data.api

import com.kim344.data.model.RandomUserResponse
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApiInterface {

    @GET("/?")
    fun getRandomUserData(
        @Query("results") results: String
    ): Single<RandomUserResponse>

    companion object {
        fun create(): RandomUserApiInterface {
            val logger = HttpLoggingInterceptor().apply {
                level =
                    HttpLoggingInterceptor.Level.BODY
            }
            val interceptor = Interceptor { chain ->
                with(chain) {
                    val newRequest = request().newBuilder()
                        .build()
                    proceed(newRequest)
                }
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(ApiClient.RANDOM_USER_BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RandomUserApiInterface::class.java)
        }
    }
}