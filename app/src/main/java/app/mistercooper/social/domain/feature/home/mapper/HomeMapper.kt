package app.mistercooper.social.domain.feature.home.mapper

import app.mistercooper.social.data.remote.dto.MultimediaDTO
import app.mistercooper.social.data.remote.dto.MultimediaFeedDTO
import app.mistercooper.social.domain.feature.home.model.FeedModel
import app.mistercooper.social.domain.feature.home.model.Post
import app.mistercooper.social.domain.feature.home.model.UserModel

fun MultimediaFeedDTO.toModel() = FeedModel(
    multimediaModel.map { it.toModel() },
    morePages
)

fun MultimediaDTO.toModel() = Post(
    id = id,
    user = UserModel(id = userId),
    description = description,
    imageUrl = absoluteUrl,
    totalLikes = numberOfLikes,
    totalComments = 10 // fixme!!!
)