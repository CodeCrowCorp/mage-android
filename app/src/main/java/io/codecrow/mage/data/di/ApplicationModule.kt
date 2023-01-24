package io.codecrow.mage.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.codecrow.mage.data.repository.ChannelRepository
import io.codecrow.mage.data.repository.ChannelRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun bindChannelRepository(channelRepositoryImpl: ChannelRepositoryImpl): ChannelRepository



}