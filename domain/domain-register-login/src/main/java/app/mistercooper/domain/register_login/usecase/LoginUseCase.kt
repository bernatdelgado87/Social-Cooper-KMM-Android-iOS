package app.mistercooper.domain.register_login.usecase

import app.mistercooper.domain.common.arch.usecase.UseCase
import app.mistercooper.domain.register_login.model.LoginUserModel
import app.mistercooper.domain.register_login.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(val userRepository: UserRepository) :
    UseCase<Unit, LoginUserModel>() {
    override fun run(params: LoginUserModel): Flow<Unit> {
        return flow { emit(userRepository.login(params)) }
    }

}