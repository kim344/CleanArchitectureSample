package com.kim344.domain.test

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TestModule {

    @Provides
    @Singleton
    fun provideName(): String {
        return "TEST Value"
    }

    @Provides
    @Singleton
    fun provideTestRepo(
        str: String
    ): TestRepository {
        return TestRepositoryImpl(str)
    }

}