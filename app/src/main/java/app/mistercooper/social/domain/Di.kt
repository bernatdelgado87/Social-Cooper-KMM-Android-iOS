package app.mistercooper.social.domain

import android.content.Context
import app.mistercooper.social.data.MediaRepositoryImpl
import app.mistercooper.social.data.SocialRepositoryImpl
import app.mistercooper.social.data.remote.api.CooperApi
import app.mistercooper.social.domain.repository.MediaRepository
import app.mistercooper.social.domain.repository.SocialRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Di {
        @Provides
        @Singleton
        fun providesSocialRepository(api: CooperApi): SocialRepository = SocialRepositoryImpl(api)


        @Provides
        @Singleton
        fun providesMediaRepository(@ApplicationContext context: Context): MediaRepository = MediaRepositoryImpl(context)

}