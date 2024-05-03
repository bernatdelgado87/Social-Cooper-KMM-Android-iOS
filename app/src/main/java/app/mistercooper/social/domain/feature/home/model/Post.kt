package app.mistercooper.social.domain.feature.home.model

import java.util.Date

data class Post(val id: Long,
                val user: UserModel,
                val description: String?,
                val imageUrl: String,
                val totalLikes: Int,
                val totalComments: Int)

data class Comment(val text: String,
                   val date: Date,
                   val user: UserModel,
                   val relatedComment: Comment? = null)