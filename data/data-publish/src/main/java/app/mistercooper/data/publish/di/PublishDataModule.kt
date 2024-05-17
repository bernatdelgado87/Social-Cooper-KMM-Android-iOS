package app.mistercooper.data.publish.di

import app.mistercooper.data.common.di.annotations.AuthOkHttpClient
import app.mistercooper.data.publish.remote.api.PublishPostApi
import app.mistercooper.data.publish.repository.PublishRepositoryImpl
import app.mistercooper.domain.publish.repository.PublishRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class PublishDataModule {
    @Provides
    @Singleton
    fun providePublishApiService(
        @AuthOkHttpClient retrofit: Retrofit
    ): PublishPostApi.Service {
        return retrofit.create(PublishPostApi.Service::class.java)
    }

    @Provides
    @Singleton
    fun providePublishRepository(
        feedRemoteApi: PublishPostApi
    ): PublishRepository {
        return PublishRepositoryImpl(feedRemoteApi)
    }
}