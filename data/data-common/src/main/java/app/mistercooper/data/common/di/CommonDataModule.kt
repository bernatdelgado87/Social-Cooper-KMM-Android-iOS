package app.mistercooper.data.common.di

import app.mistercooper.data.common.di.annotations.NotAuthOkHttpClient
import app.mistercooper.data.common.local.LocalUserDataSource
import app.mistercooper.data.common.remote.api.CommonApi.Companion.SOCIAL_API_URL
import app.mistercooper.data.common.remote.api.CommonApi.Companion.USER_API_URL
import app.mistercooper.data.common.remote.interceptor.AuthInterceptor
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
class CommonDataModule {
    @Provides
    @Singleton
    fun provideAuthInterceptor(localUserDataSource: LocalUserDataSource) =
        AuthInterceptor(localUserDataSource)
    @Provides
    @Singleton
    @app.mistercooper.data.common.di.annotations.AuthOkHttpClient
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
    @app.mistercooper.data.common.di.annotations.AuthOkHttpClient
    fun provideAuthRetrofit(
        @app.mistercooper.data.common.di.annotations.AuthOkHttpClient okHttpClient: OkHttpClient
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
}