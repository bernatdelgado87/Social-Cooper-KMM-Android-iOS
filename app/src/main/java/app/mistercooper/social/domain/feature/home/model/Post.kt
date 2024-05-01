package app.mistercooper.social.domain.feature.home.model

data class Post(val id: Int,
                val user: UserModel,
                val description: String,
                val url: String,
                val numberOfLikes: Int)