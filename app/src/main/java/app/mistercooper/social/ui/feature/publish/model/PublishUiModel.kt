package app.mistercooper.social.ui.feature.publish.model

data class PublishUiModel(
    val loading: Boolean = false,
    val error: Boolean = false,
    val postPublishedSuccess: Boolean = false
)
