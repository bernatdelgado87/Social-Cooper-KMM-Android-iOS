package app.mistercooper.social.data.remote.dto

import kotlinx.serialization.Serializable as Serializable

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
    val id: Int? = null,
    val userId: Int? = null,
    val description: String? = null,
    val relativeUrl: String? = null,
    val absoluteUrl: String? = null,
    val numberOfLikes: Int? = null,
    val likes: List<LikeSimplifiedDTO>? = null)



