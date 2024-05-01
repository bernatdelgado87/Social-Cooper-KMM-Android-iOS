package app.mistercooper.social.data.di

import app.mistercooper.social.data.remote.api.CooperApi
import app.mistercooper.social.data.remote.api.CooperApi.Companion.API_URL
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
class ApiModule {
        @Provides
        @Singleton
        fun provideAuthInterceptorOkHttpClient(): OkHttpClient {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val builder = OkHttpClient.Builder()
                builder.addInterceptor(logging)
                return builder.build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(
                okHttpClient: OkHttpClient
        ): Retrofit {
                return Retrofit
                        .Builder()
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(API_URL)
                        .build()
        }

        @Provides
        @Singleton
        fun provideGokiBankApiService(
                retrofit: Retrofit
        ): CooperApi.Service {
                return retrofit.create(CooperApi.Service::class.java)
        }

}