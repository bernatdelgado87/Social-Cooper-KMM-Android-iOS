package app.mistercooper.social.data.remote.dto.request

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class RegisterUserRequestDTO(val name: String, val email: String, val password: String)
