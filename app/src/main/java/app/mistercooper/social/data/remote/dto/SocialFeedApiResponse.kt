package app.mistercooper.social.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MultimediaFeedDTO(
    val multimediaModel: List<MultimediaDTO>,
    val morePages: Boolean
)

@Serializable
data class LikeSimplifiedDTO (
    val userId: String?,
    val postReference: Int?)

@Serializable
data class MultimediaDTO (
    val id: Long,
    val userId: Int,
    val description: String? = null,
    val relativeUrl: String,
    val absoluteUrl: String,
    val numberOfLikes: Int = 0,
    val likes: List<LikeSimplifiedDTO>? = null)



