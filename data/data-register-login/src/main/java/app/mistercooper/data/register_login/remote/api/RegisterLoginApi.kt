package app.mistercooper.data.register_login.remote.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.File
import javax.inject.Inject


class RegisterLoginApi @Inject constructor(private val service: Service) {
    suspend fun registerUser(
        name: String, email: String, password: String, imageProfile: File
    ): Response<app.mistercooper.data.register_login.remote.dto.response.RegisterUserResponseDTO> {

        val requestBody = MultipartBody.Builder().apply {
            setType(MultipartBody.FORM)
            addFormDataPart("name", name)
            addFormDataPart("email", email)
            addFormDataPart("password", password)
            addFormDataPart("file", imageProfile.name, imageProfile.asRequestBody())
        }.build()
        return service.registerUser(requestBody)


    }

    suspend fun login(loginRequestDTO: app.mistercooper.data.register_login.remote.dto.request.LoginRequestDTO): Response<app.mistercooper.data.register_login.remote.dto.response.RegisterUserResponseDTO> {
        return service.login(loginRequestDTO)
    }

    interface Service {
        @POST("register")
        suspend fun registerUser(
            @Body body: RequestBody
        ): Response<app.mistercooper.data.register_login.remote.dto.response.RegisterUserResponseDTO>

        @POST("login")
        suspend fun login(
            @Body body: app.mistercooper.data.register_login.remote.dto.request.LoginRequestDTO
        ): Response<app.mistercooper.data.register_login.remote.dto.response.RegisterUserResponseDTO>

    }

    companion object {
    }
}