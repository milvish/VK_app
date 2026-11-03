package com.lizina.vkapp.di

import com.lizina.vkapp.data.repository.AppDetailsRepositoryImpl
import com.lizina.vkapp.domain.appdetails.AppDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAppDetailsRepository(
        impl: AppDetailsRepositoryImpl
    ): AppDetailsRepository
}