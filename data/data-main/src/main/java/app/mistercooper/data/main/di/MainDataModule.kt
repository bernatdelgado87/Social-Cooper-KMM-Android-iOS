package app.mistercooper.data.home.di

import app.mistercooper.data.common.local.LocalUserDataSource
import app.mistercooper.data.home.repository.MainRepositoryImpl
import app.mistercooper.domain.common.feature.user.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MainDataModule {
    @Provides
    @Singleton
    fun provideMainRepository(
        localUserDataSource: LocalUserDataSource
    ): MainRepository {
        return MainRepositoryImpl(localUserDataSource)
    }
}