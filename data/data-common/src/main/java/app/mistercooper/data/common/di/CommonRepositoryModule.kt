package app.mistercooper.data.common.di

import android.content.Context
import app.mistercooper.data.common.repository.MediaRepositoryImpl
import app.mistercooper.domain.common.feature.media.repository.MediaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CommonRepositoryModule {

    @Provides
    @Singleton
    fun provideCommonMediaRepository(
        @ApplicationContext context: Context
    ): MediaRepository {
        return MediaRepositoryImpl(context)
    }
}