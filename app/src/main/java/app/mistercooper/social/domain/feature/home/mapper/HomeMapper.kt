package app.mistercooper.social.domain.feature.home.mapper

import app.mistercooper.social.data.remote.dto.response.MultimediaDTO
import app.mistercooper.social.data.remote.dto.response.MultimediaFeedDTO
import app.mistercooper.social.domain.feature.home.model.FeedModel
import app.mistercooper.social.domain.feature.home.model.PostModel
import app.mistercooper.social.domain.feature.home.model.UserModel

fun MultimediaFeedDTO.toModel() = FeedModel(
    multimediaModel.map { it.toModel() },
    morePages
)

fun MultimediaDTO.toModel() = PostModel(
    id = id,
    user = UserModel(id = user.id, userName = user.name, imageProfileUrl = user.profileImage),
    description = description,
    imageUrl = absoluteUrl,
    totalLikes = numberOfLikes,
    totalComments = numberOfComments
)