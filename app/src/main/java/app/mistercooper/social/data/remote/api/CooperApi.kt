package app.mistercooper.social.data.remote.api

import app.mistercooper.social.data.remote.dto.request.LoginRequestDTO
import app.mistercooper.social.data.remote.dto.response.MultimediaFeedDTO
import app.mistercooper.social.data.remote.dto.request.RegisterUserRequestDTO
import app.mistercooper.social.data.remote.dto.response.RegisterUserResponseDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.io.File
import javax.inject.Inject


class CooperApi @Inject constructor(private val service: Service) {

    suspend fun getSocialFeed(): Response<MultimediaFeedDTO> = service.getFeed()
    suspend fun publishPost(text: String, file: File): Response<MultimediaFeedDTO> {
        val requestBody = MultipartBody.Builder().apply {
            setType(MultipartBody.FORM)
            addFormDataPart("userId", "1")
            addFormDataPart("description", text)
            addFormDataPart("file", file.name, file.asRequestBody())
        }.build()
        return service.publishPost(requestBody)
    }

    suspend fun registerUser(registerUserRequestDTO: RegisterUserRequestDTO): Response<RegisterUserResponseDTO> {
        return service.registerUser(registerUserRequestDTO)
    }

    suspend fun login(loginRequestDTO: LoginRequestDTO): Response<RegisterUserResponseDTO> {
        return service.login(loginRequestDTO)
    }

    interface Service {
        @GET("social/feed")
        suspend fun getFeed(): Response<MultimediaFeedDTO>

        @POST("social/upload")
        suspend fun publishPost(
            @Body body: RequestBody
        ): Response<MultimediaFeedDTO>

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