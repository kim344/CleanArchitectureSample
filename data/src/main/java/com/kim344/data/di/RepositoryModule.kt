package com.kim344.data.di

import com.kim344.data.repository.LoginRepositoryImpl
import com.kim344.data.repository.local.LoginLocalDataSource
import com.kim344.domain.repository.LoginRepository
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
    fun provideLoginRepository(loginLocalDataSource: LoginLocalDataSource): LoginRepository {
        return LoginRepositoryImpl(loginLocalDataSource)
    }

}