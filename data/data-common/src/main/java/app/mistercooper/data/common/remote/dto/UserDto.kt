package app.mistercooper.data.common.remote.dto

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class UserDTO (
    val id: Int,
    val name: String? = null,
    val profileImage: String? = null
)
