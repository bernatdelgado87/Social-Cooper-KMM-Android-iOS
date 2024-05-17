package app.mistercooper.data.publish.remote.dto.mapper

import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

fun generateMultiPartBody(text: String, file: File) = MultipartBody.Builder().apply {
        setType(MultipartBody.FORM)
        addFormDataPart("description", text)
        addFormDataPart("file", file.name, file.asRequestBody())
    }.build()