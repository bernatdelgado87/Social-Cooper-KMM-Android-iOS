package app.mistercooper.data.comment.remote.dto.mapper

import app.mistercooper.domain.common.utils.convertDateFromServer
import app.mistercooper.domain.common.feature.user.model.UserModel
import app.mistercooper.data.comment.remote.dto.response.CommentDTO
import app.mistercooper.data.comment.remote.dto.response.CommentResponseDTO

fun CommentDTO.toModel() = app.mistercooper.domain.comment.model.CommentModel(
    content,
    createTime.convertDateFromServer(),
    UserModel(
        id = user.id,
        userName = user.name,
        imageProfileUrl = user.profileImage
    )
)

fun CommentResponseDTO.toModel() = app.mistercooper.domain.comment.model.CommentWrapperModel(
    imageUrl,
    comments.map { comment -> comment.toModel() })