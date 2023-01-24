package io.codecrow.mage.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.codecrow.mage.data.datasource.ChannelRemote
import io.codecrow.mage.data.datasource.ChannelRemoteImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindChannelRemote(channelRemoteImpl: ChannelRemoteImpl): ChannelRemote
}