package com.kim344.data.api

import com.kim344.data.api.ApiClient.USER_BASE_URL
import com.kim344.data.model.UserResponse
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiInterface {

    @GET("{userId}")
    fun getUserData(
        @Path("userId") userId: String
    ): Single<UserResponse>

    companion object {
        fun create(): UserApiInterface {
            val logger = HttpLoggingInterceptor().apply {
                level =
                    HttpLoggingInterceptor.Level.BODY
            }
            val interceptor = Interceptor { chain ->
                with(chain) {
                    val newRequest = request().newBuilder()
                        /*
                        .addHeader("X-Naver-Client-Id", "33chRuAiqlSn5hn8tIme")
                        .addHeader("X-Naver-Client-Secret", "fyfwt9PCUN")
                        */
                        .build()
                    proceed(newRequest)
                }
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(USER_BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserApiInterface::class.java)
        }
    }

}