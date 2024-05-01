package app.mistercooper.social.domain

import app.mistercooper.social.data.remote.SocialRepositoryImpl
import app.mistercooper.social.data.remote.api.CooperApi
import app.mistercooper.social.domain.repository.SocialRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Di {
        @Provides
        @Singleton
        fun providesSocialRepository(api: CooperApi): SocialRepository = SocialRepositoryImpl(api)

}