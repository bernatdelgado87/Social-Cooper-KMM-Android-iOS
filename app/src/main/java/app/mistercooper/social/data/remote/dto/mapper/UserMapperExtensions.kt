package app.mistercooper.social.data.remote.dto.mapper

import app.mistercooper.social.data.remote.dto.request.LoginRequestDTO
import app.mistercooper.social.domain.feature.user.model.LoginUserModel

fun LoginUserModel.toDTO() = LoginRequestDTO(email, password)
