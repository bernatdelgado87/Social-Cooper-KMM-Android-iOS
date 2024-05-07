package app.mistercooper.social.domain.feature.user.usecase

import app.mistercooper.social.domain.common.UseCase
import app.mistercooper.social.domain.feature.user.model.LoginUserModel
import app.mistercooper.social.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(val userRepository: UserRepository) :
    UseCase<Unit, LoginUserModel>() {
    override fun run(params: LoginUserModel): Flow<Unit> {
        return flow { emit(userRepository.login(params)) }
    }

}