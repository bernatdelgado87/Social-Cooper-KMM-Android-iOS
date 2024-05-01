package app.mistercooper.social.domain.feature.home.model

data class FeedModel(
    val posts: List<Post>,
    val hasMorePages: Boolean
)