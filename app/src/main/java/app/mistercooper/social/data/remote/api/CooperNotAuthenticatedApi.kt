package app.mistercooper.social.data.remote.api

import app.mistercooper.social.data.remote.dto.request.LoginRequestDTO
import app.mistercooper.social.data.remote.dto.response.RegisterUserResponseDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.File
import javax.inject.Inject


class CooperNotAuthenticatedApi @Inject constructor(private val service: Service) {
    suspend fun registerUser(
        name: String, email: String, password: String, imageProfile: File
    ): Response<RegisterUserResponseDTO> {

        val requestBody = MultipartBody.Builder().apply {
            setType(MultipartBody.FORM)
            addFormDataPart("name", name)
            addFormDataPart("email", email)
            addFormDataPart("password", password)
            addFormDataPart("file", imageProfile.name, imageProfile.asRequestBody())
        }.build()
        return service.registerUser(requestBody)


    }

    suspend fun login(loginRequestDTO: LoginRequestDTO): Response<RegisterUserResponseDTO> {
        return service.login(loginRequestDTO)
    }

    interface Service {
        @POST("register")
        suspend fun registerUser(
            @Body body: RequestBody
        ): Response<RegisterUserResponseDTO>

        @POST("login")
        suspend fun login(
            @Body body: LoginRequestDTO
        ): Response<RegisterUserResponseDTO>

    }

    companion object {
        const val USER_API_URL = "http://192.168.1.235:8080/"
    }
}