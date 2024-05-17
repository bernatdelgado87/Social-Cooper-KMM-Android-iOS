package app.mistercooper.domain.register_login.model

import java.io.File

data class RegisterUserModel(val name: String, val email: String, val password: String, val imageProfile: File)
