package app.mistercooper.social.domain.feature.home.model

data class UserModel(
    val id: Int,
    val userName: String? = null,
    val imageProfileUrl: String? = null,
)
