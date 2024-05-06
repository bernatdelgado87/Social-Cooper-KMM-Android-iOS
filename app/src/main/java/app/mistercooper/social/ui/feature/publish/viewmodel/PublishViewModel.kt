package app.mistercooper.social.ui.feature.publish.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mistercooper.social.domain.feature.publish.usecase.GetImageFromUriUseCase
import app.mistercooper.social.domain.feature.publish.usecase.GetMediaImagesFromDeviceUseCase
import app.mistercooper.social.domain.feature.publish.usecase.PublishPostUseCase
import app.mistercooper.social.ui.feature.publish.model.PublishUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class PublishViewModel @Inject constructor(
    private val getMediaImagesFromDeviceUseCase: GetMediaImagesFromDeviceUseCase,
    private val getImageFromUriUseCase: GetImageFromUriUseCase,
    private val publishPostUseCase: PublishPostUseCase
) : ViewModel() {
    private val _publishUiModelState = MutableStateFlow(
        PublishUiModel()
    )
    val publishUiModelState = _publishUiModelState.asStateFlow()

    var selectedFile: MutableStateFlow<File?> = MutableStateFlow(null)

    fun getLocalMediaImages() {
        viewModelScope.launch {
            getMediaImagesFromDeviceUseCase()
                .catch {
                    it.printStackTrace()
                    _publishUiModelState.emit(PublishUiModel(error = true))

                }.collect { response ->
                    _publishUiModelState.emit(PublishUiModel(localImages = response))
                }
        }
    }

    fun onPhotoSelected(photo: Uri?) {
        viewModelScope.launch {
            photo?.let {
                getImageFromUriUseCase(photo).collect {file ->
                    selectedFile.emit(file)
                }
            }?:
            _publishUiModelState.emit(PublishUiModel(error = true))
        }
    }

    fun publishPost(text: String){
        selectedFile.value?.let {
            viewModelScope.launch {
                publishPostUseCase(PublishPostUseCase.PublishPostParams(text, selectedFile.value!!))
                    .onStart {
                        _publishUiModelState.emit(PublishUiModel(loading = true))
                    }
                    .catch {
                        it.printStackTrace()
                        _publishUiModelState.emit(PublishUiModel(error = true))
                    }.collect { response ->
                        _publishUiModelState.emit(PublishUiModel(postPublishedSuccess = true))
                    }
            }
        }
    }

    fun clearState(){
        viewModelScope.launch {
            _publishUiModelState.emit(PublishUiModel())
        }
    }
}