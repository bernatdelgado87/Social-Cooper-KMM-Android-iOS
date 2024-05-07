package app.mistercooper.social.domain.feature.user.usecase

import app.mistercooper.social.domain.common.UseCase
import app.mistercooper.social.domain.feature.user.model.RegisterUserModel
import app.mistercooper.social.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(val userRepository: UserRepository) :
    UseCase<Unit, RegisterUserModel>() {
    override fun run(params: RegisterUserModel): Flow<Unit> {
        return flow { emit(userRepository.registerUser(params)) }
    }

}