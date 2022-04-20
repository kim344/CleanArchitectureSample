package com.kim344.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class TestCaseModule {

    @Provides
    @Singleton
    @Named("stringValue")
    fun provideStringValue(): String {
        return "HiltStringValue"
    }

}