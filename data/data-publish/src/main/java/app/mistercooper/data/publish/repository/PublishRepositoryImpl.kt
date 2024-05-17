package app.mistercooper.data.publish.repository

import app.mistercooper.data.publish.remote.api.PublishPostApi
import app.mistercooper.domain.common.arch.model.GlobalFailure
import app.mistercooper.domain.publish.repository.PublishRepository
import java.io.File
import javax.inject.Inject

class PublishRepositoryImpl @Inject constructor(private val publishPostApi: PublishPostApi): PublishRepository {
    override suspend fun publishPost(text: String, file: File) {
        try {
            val response = publishPostApi.publishPost(text, file)
            if (response.isSuccessful) {
                response.body()?.let {
                    return
                }
            }
            throw GlobalFailure.GlobalError()
        } catch (e: Exception) {
            e.printStackTrace()
            throw GlobalFailure.GlobalError(e)
        }
    }
}