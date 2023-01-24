package io.codecrow.mage.ui.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.codecrow.mage.data.service.ChannelApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {

    @Provides
    @Singleton
    fun provideChannelService(retrofit: Retrofit): ChannelApi {
        return retrofit.create(ChannelApi::class.java)
    }
}