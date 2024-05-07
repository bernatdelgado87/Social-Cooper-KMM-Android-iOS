package app.mistercooper.social.domain

import android.content.Context
import app.mistercooper.social.data.MediaRepositoryImpl
import app.mistercooper.social.data.SocialRepositoryImpl
import app.mistercooper.social.data.UserRepositoryImpl
import app.mistercooper.social.data.local.LocalUserDataSource
import app.mistercooper.social.data.remote.api.CooperAuthenticatedApi
import app.mistercooper.social.data.remote.api.CooperNotAuthenticatedApi
import app.mistercooper.social.domain.repository.MediaRepository
import app.mistercooper.social.domain.repository.SocialRepository
import app.mistercooper.social.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {
        @Provides
        @Singleton
        fun providesSocialRepository(api: CooperAuthenticatedApi): SocialRepository = SocialRepositoryImpl(api)


        @Provides
        @Singleton
        fun providesMediaRepository(@ApplicationContext context: Context): MediaRepository = MediaRepositoryImpl(context)

        @Provides
        @Singleton
        fun providesUserRepository(api: CooperNotAuthenticatedApi, localUserDataSource: LocalUserDataSource): UserRepository = UserRepositoryImpl(api, localUserDataSource)
}