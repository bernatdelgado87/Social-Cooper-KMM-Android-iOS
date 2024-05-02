package app.mistercooper.social.ui.comment.model

import app.mistercooper.social.domain.feature.home.model.Comment

data class PublishCommentUiModel(
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val comments: List<Comment>? = null
)