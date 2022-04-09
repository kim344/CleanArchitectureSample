package com.kim344.domain.di

import com.kim344.domain.repository.LoginRepository
import com.kim344.domain.repository.MovieRepository
import com.kim344.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetLocalMovieUseCase(movieRepository: MovieRepository): GetLocalMoviesUseCase {
        return GetLocalMoviesUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideGetLoginUseCase(loginRepository: LoginRepository): GetLoginUseCase {
        return GetLoginUseCase(loginRepository)
    }

    @Provides
    @Singleton
    fun provideGetMovieUseCase(movieRepository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun providesPagingMovieUseCase(movieRepository: MovieRepository): GetPagingMovieUseCase {
        return GetPagingMovieUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun providesInsertLoginUseCase(loginRepository: LoginRepository): InsertLoginUseCase {
        return InsertLoginUseCase(loginRepository)
    }
}