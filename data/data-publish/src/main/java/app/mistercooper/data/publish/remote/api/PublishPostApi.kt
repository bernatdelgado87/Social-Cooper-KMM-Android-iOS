package app.mistercooper.data.publish.remote.api

import app.mistercooper.data.publish.remote.dto.mapper.generateMultiPartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.File
import javax.inject.Inject

class PublishPostApi @Inject constructor(private val service: Service) {

    suspend fun publishPost(
        text: String,
        file: File
    ): Response<Unit> {
        val requestBody = generateMultiPartBody(text, file)
        return service.publishPost(requestBody)
    }

    interface Service {
        @POST("upload")
        suspend fun publishPost(
            @Body body: RequestBody
        ): Response<Unit>
    }
}