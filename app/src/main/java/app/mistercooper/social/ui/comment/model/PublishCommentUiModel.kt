package app.mistercooper.social.ui.comment.model

import app.mistercooper.social.domain.feature.home.model.Comment

data class PublishCommentUiModel(
    val isError: Boolean = false,
    val isLoadingComments: Boolean = false,
    val isLoadingPublish: Boolean = false,
    val comments: List<Comment>? = null
)