package com.kim344.data.di

import com.kim344.data.api.ApiInterface
import com.kim344.data.api.RandomUserApiInterface
import com.kim344.data.api.UserApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideApiInterface(): ApiInterface {
        return ApiInterface.create()
    }

    @Provides
    @Singleton
    fun provideUserApiInterface(): UserApiInterface {
        return UserApiInterface.create()
    }

    @Provides
    @Singleton
    fun provideRandomUserApiInterface(): RandomUserApiInterface {
        return RandomUserApiInterface.create()
    }
}