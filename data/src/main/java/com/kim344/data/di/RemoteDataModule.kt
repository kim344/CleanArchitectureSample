package com.kim344.data.di

import com.kim344.data.api.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

//    @Provides
//    @Singleton
//    fun provideMovieRemoteSource(apiInterface: ApiInterface): MovieRe

}