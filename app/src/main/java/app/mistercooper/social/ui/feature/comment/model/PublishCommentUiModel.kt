package app.mistercooper.social.ui.feature.comment.model

import app.mistercooper.social.domain.feature.comment.model.CommentWrapperModel

data class PublishCommentUiModel(
    val isError: Boolean = false,
    val isLoadingComments: Boolean = false,
    val isLoadingPublish: Boolean = false,
    val commentWrapper: CommentWrapperModel? = null
)