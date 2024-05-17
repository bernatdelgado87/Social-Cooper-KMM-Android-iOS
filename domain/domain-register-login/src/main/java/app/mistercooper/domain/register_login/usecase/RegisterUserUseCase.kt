package app.mistercooper.domain.register_login.usecase

import app.mistercooper.domain.common.arch.usecase.UseCase
import app.mistercooper.domain.register_login.model.RegisterUserModel
import app.mistercooper.domain.register_login.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(val userRepository: UserRepository) :
    UseCase<Unit, RegisterUserModel>() {
    override fun run(params: RegisterUserModel): Flow<Unit> {
        return flow { emit(userRepository.registerUser(params)) }
    }

}