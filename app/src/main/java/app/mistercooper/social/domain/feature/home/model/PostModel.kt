package app.mistercooper.social.domain.feature.home.model

data class PostModel(val id: Long,
                     val user: UserModel,
                     val description: String?,
                     val imageUrl: String,
                     val totalLikes: Int,
                     val totalComments: Int)