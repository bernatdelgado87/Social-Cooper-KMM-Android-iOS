package app.mistercooper.social.domain.feature.user.usecase

import app.mistercooper.social.domain.common.None
import app.mistercooper.social.domain.common.UseCase
import app.mistercooper.social.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IsUserRegisteredUseCase @Inject constructor(val userRepository: UserRepository) :
    UseCase<Boolean, None>() {
    override fun run(params: None): Flow<Boolean> {
        return flow { emit(userRepository.isUserRegistered()) }
    }

}