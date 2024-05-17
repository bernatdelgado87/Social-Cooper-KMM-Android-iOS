package app.mistercooper.social.application.utils

import app.mistercooper.ui.common.utils.BuildConfigFields
import app.mistercooper.ui.common.utils.BuildConfigFieldsProvider
import app.mistercooper.social.BuildConfig

class ApplicationBuildConfigFieldsProvider : BuildConfigFieldsProvider {

    override fun get(): BuildConfigFields = BuildConfigFields(
        buildType = BuildConfig.BUILD_TYPE,
        versionCode = BuildConfig.VERSION_CODE,
        versionName = BuildConfig.VERSION_NAME,
        appId = BuildConfig.APPLICATION_ID
    )
}