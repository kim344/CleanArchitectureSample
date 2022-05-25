package com.kim344.data.di

import com.kim344.data.repository.login.LoginRepositoryImpl
import com.kim344.data.repository.login.local.LoginLocalDataSource
import com.kim344.data.repository.search.MovieRepositoryImpl
import com.kim344.data.repository.search.local.MovieLocalDataSource
import com.kim344.data.repository.search.remote.MovieRemoteDatasource
import com.kim344.domain.repository.LoginRepository
import com.kim344.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDatasource,
        movieLocalDataSource: MovieLocalDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(loginLocalDataSource: LoginLocalDataSource): LoginRepository {
        return LoginRepositoryImpl(loginLocalDataSource)
    }

}