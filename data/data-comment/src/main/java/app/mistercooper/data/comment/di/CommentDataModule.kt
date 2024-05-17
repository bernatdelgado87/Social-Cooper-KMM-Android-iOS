package app.mistercooper.data.comment.di

import app.mistercooper.data.comment.remote.api.CommentRemoteApi
import app.mistercooper.data.comment.repository.CommentRepositoryImpl
import app.mistercooper.data.common.di.annotations.AuthOkHttpClient
import app.mistercooper.domain.comment.repository.CommentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CommentDataModule {
    @Provides
    @Singleton
    fun provideCommentApiService(
        @AuthOkHttpClient retrofit: Retrofit
    ): CommentRemoteApi.Service {
        return retrofit.create(CommentRemoteApi.Service::class.java)
    }

    @Provides
    @Singleton
    fun provideCommentRepository(
        commentRemoteApi: CommentRemoteApi
    ): CommentRepository {
        return CommentRepositoryImpl(commentRemoteApi)
    }
}