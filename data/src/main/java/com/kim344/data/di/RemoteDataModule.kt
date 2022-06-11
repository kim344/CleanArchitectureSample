package com.kim344.data.di

import com.kim344.data.api.ApiInterface
import com.kim344.data.api.RandomUserApiInterface
import com.kim344.data.api.UserApiInterface
import com.kim344.data.repository.search.remote.MovieRemoteDataSourceImpl
import com.kim344.data.repository.search.remote.MovieRemoteDatasource
import com.kim344.data.repository.user.remote.UserRemoteDataSourceImpl
import com.kim344.data.repository.user.remote.UserRemoteDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Provides
    @Singleton
    fun provideMovieRemoteSource(apiInterface: ApiInterface): MovieRemoteDatasource {
        return MovieRemoteDataSourceImpl(apiInterface)
    }

    @Provides
    @Singleton
    fun provideUserRemoteSource(userApiInterface: UserApiInterface, randomUserApiInterface: RandomUserApiInterface): UserRemoteDatasource {
        return UserRemoteDataSourceImpl(userApiInterface, randomUserApiInterface)
    }

}