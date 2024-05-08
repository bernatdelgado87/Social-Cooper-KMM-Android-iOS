package app.mistercooper.social.data.di

import app.mistercooper.social.data.di.annotations.AuthOkHttpClient
import app.mistercooper.social.data.di.annotations.NotAuthOkHttpClient
import app.mistercooper.social.data.local.LocalUserDataSource
import app.mistercooper.social.data.remote.api.CooperAuthenticatedApi
import app.mistercooper.social.data.remote.api.CooperAuthenticatedApi.Companion.SOCIAL_API_URL
import app.mistercooper.social.data.remote.api.CooperNotAuthenticatedApi
import app.mistercooper.social.data.remote.api.CooperNotAuthenticatedApi.Companion.USER_API_URL
import app.mistercooper.social.data.remote.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

        @Provides
        @Singleton
        fun provideAuthInterceptor(localUserDataSource: LocalUserDataSource) = AuthInterceptor(localUserDataSource)
        @Provides
        @Singleton
        @AuthOkHttpClient
        fun provideAuthOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val builder = OkHttpClient.Builder()
                builder.addInterceptor(authInterceptor)
                builder.addInterceptor(logging)
                return builder.build()
        }

        @Provides
        @Singleton
        @NotAuthOkHttpClient
        fun provideNotAuthOkHttpClient(): OkHttpClient {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val builder = OkHttpClient.Builder()
                builder.addInterceptor(logging)
                return builder.build()
        }

        @Provides
        @Singleton
        @AuthOkHttpClient
        fun provideAuthRetrofit(
                @AuthOkHttpClient okHttpClient: OkHttpClient
        ): Retrofit {
                return Retrofit
                        .Builder()
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(SOCIAL_API_URL)
                        .build()
        }

        @Provides
        @Singleton
        @NotAuthOkHttpClient
        fun provideNotAuthRetrofit(
                @NotAuthOkHttpClient okHttpClient: OkHttpClient
        ): Retrofit {
                return Retrofit
                        .Builder()
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(USER_API_URL)
                        .build()
        }

        @Provides
        @Singleton
        fun provideAuthApiService(
                @AuthOkHttpClient retrofit: Retrofit
        ): CooperAuthenticatedApi.Service {
                return retrofit.create(CooperAuthenticatedApi.Service::class.java)
        }

        @Provides
        @Singleton
        fun provideNotAuthBankApiService(
                @NotAuthOkHttpClient retrofit: Retrofit
        ): CooperNotAuthenticatedApi.Service {
                return retrofit.create(CooperNotAuthenticatedApi.Service::class.java)
        }

}