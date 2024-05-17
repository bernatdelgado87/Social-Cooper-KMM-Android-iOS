package app.mistercooper.data.home.di

import app.mistercooper.data.common.di.annotations.AuthOkHttpClient
import app.mistercooper.data.home.remote.api.FeedRemoteApi
import app.mistercooper.data.home.repository.HomeRepositoryImpl
import app.mistercooper.domain.home.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class HomeDataModule {
    @Provides
    @Singleton
    fun provideHomeApiService(
        @AuthOkHttpClient retrofit: Retrofit
    ): FeedRemoteApi.Service {
        return retrofit.create(FeedRemoteApi.Service::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(
        feedRemoteApi: FeedRemoteApi
    ): HomeRepository {
        return HomeRepositoryImpl(feedRemoteApi)
    }
}