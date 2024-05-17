package app.mistercooper.ui.comment.model

import app.mistercooper.domain.comment.model.CommentWrapperModel


data class PublishCommentUiModel(
    val isError: Boolean = false,
    val isLoadingComments: Boolean = false,
    val isLoadingPublish: Boolean = false,
    val commentWrapper: CommentWrapperModel? = null
)