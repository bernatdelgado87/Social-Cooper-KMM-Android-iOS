package app.mistercooper.domain.common.feature.user.model

data class UserModel(
    val id: Int,
    val userName: String? = null,
    val imageProfileUrl: String? = null,
)
