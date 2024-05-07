package app.mistercooper.social.data.remote.api

import app.mistercooper.social.data.remote.dto.request.LoginRequestDTO
import app.mistercooper.social.data.remote.dto.request.RegisterUserRequestDTO
import app.mistercooper.social.data.remote.dto.response.RegisterUserResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject


class CooperNotAuthenticatedApi @Inject constructor(private val service: Service) {
    suspend fun registerUser(registerUserRequestDTO: RegisterUserRequestDTO): Response<RegisterUserResponseDTO> {
        return service.registerUser(registerUserRequestDTO)
    }

    suspend fun login(loginRequestDTO: LoginRequestDTO): Response<RegisterUserResponseDTO> {
        return service.login(loginRequestDTO)
    }

    interface Service {
        @POST("register")
        suspend fun registerUser(
            @Body body: RegisterUserRequestDTO
        ): Response<RegisterUserResponseDTO>

        @POST("login")
        suspend fun login(
            @Body body: LoginRequestDTO
        ): Response<RegisterUserResponseDTO>

    }

    companion object {
        const val API_URL = "http://192.168.1.235:8080/"
    }
}