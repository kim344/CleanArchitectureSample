package com.kim344.data.di

import com.kim344.data.api.ApiInterface
import com.kim344.data.repository.search.remote.MovieRemoteDataSourceImpl
import com.kim344.data.repository.search.remote.MovieRemoteDatasource
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

}