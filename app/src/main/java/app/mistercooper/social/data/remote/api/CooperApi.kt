package app.mistercooper.social.data.remote.api

import app.mistercooper.social.data.remote.dto.MultimediaFeedDTO
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject

class CooperApi @Inject constructor(private val service: Service) {

    suspend fun getSocialFeed(): Response<MultimediaFeedDTO> = service.getFeed()

    interface Service {
        @GET("social/feed")
        suspend fun getFeed(): Response<MultimediaFeedDTO>
    }

    companion object {
        const val API_URL = "http://192.168.1.235:8080/"
    }
}