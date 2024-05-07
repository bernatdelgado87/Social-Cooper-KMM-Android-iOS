package app.mistercooper.social.data.di

import android.content.Context
import android.content.SharedPreferences
import app.mistercooper.social.data.local.LocalUserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalDataModule {

    @Provides
    @Singleton
    fun provideSharedPrefs(@ApplicationContext context: Context) = context.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)



        @Provides
        @Singleton
        fun providesLocalDataSource(sharedPrefs: SharedPreferences): LocalUserDataSource = LocalUserDataSource(sharedPrefs)
}