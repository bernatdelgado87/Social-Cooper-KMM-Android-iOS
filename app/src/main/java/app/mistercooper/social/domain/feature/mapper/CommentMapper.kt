package app.mistercooper.social.domain.feature.mapper

import app.mistercooper.social.data.remote.dto.response.CommentDTO
import app.mistercooper.social.data.remote.dto.response.CommentResponseDTO
import app.mistercooper.social.domain.common.mapper.convertDateFromServer
import app.mistercooper.social.domain.feature.comment.model.CommentModel
import app.mistercooper.social.domain.feature.comment.model.CommentWrapperModel
import app.mistercooper.social.domain.feature.home.model.UserModel

fun CommentDTO.toModel() = CommentModel(content, createTime.convertDateFromServer(), UserModel(id = user.id, userName = user.name, imageProfileUrl = user.profileImage))

fun CommentResponseDTO.toModel() = CommentWrapperModel(imageUrl, comments.map { comment -> comment.toModel() })