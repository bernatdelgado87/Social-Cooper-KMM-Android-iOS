package app.mistercooper.social.di

import app.mistercooper.ui.common.utils.BuildConfigFieldsProvider
import app.mistercooper.social.application.utils.ApplicationBuildConfigFieldsProvider
import app.mistercooper.social.navigation.CustomNavigatorImpl
import app.mistercooper.ui.common.navigation.CustomNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplicationBuildConfigFieldsProvider(
    ): ApplicationBuildConfigFieldsProvider = ApplicationBuildConfigFieldsProvider()

    @Provides
    @Singleton
    fun provideBuildConfigFieldsProvider(
        buildConfigFieldsProvider: ApplicationBuildConfigFieldsProvider
    ): BuildConfigFieldsProvider = buildConfigFieldsProvider


    @Provides
    @Singleton
    fun provideBottomSheetNavigator(
    ): CustomNavigator = CustomNavigatorImpl()

}