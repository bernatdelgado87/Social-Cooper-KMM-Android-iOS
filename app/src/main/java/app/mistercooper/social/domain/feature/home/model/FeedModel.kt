package app.mistercooper.social.domain.feature.home.model

data class FeedModel(
    val postModels: List<PostModel>,
    val hasMorePages: Boolean
)