package app.mistercooper.data.register_login.di

import android.content.SharedPreferences
import app.mistercooper.data.common.di.annotations.AuthOkHttpClient
import app.mistercooper.data.common.di.annotations.NotAuthOkHttpClient
import app.mistercooper.data.common.local.LocalUserDataSource
import app.mistercooper.data.register_login.remote.api.RegisterLoginApi
import app.mistercooper.data.register_login.repository.UserRepositoryImpl
import app.mistercooper.domain.register_login.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RegisterLoginDataModule {
    @Provides
    @Singleton
    fun provideRegisterLoginApiService(
        @NotAuthOkHttpClient retrofit: Retrofit
    ): RegisterLoginApi.Service {
        return retrofit.create(RegisterLoginApi.Service::class.java)
    }

    @Provides
    @Singleton
    fun provideRegisterLoginRepository(
        registerLoginApi: RegisterLoginApi,
        localUserDataSource: LocalUserDataSource
    ): UserRepository {
        return UserRepositoryImpl(registerLoginApi, localUserDataSource)
    }
}