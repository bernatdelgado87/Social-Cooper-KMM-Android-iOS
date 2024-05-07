package app.mistercooper.social.domain.feature.comment.model

data class CommentWrapperModel(
    val myImageUrl: String,
    val comments: List<CommentModel>
)