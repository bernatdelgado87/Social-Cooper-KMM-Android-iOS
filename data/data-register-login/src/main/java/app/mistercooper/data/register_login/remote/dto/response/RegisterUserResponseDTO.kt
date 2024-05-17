package app.mistercooper.data.register_login.remote.dto.response

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class RegisterUserResponseDTO(val name: String, val email: String, val password: String, val apikey: String)
