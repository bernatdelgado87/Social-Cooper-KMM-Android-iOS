package app.mistercooper.social.ui.feature.publish.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mistercooper.social.domain.feature.publish.usecase.GetImageFromUriUseCase
import app.mistercooper.social.domain.feature.publish.usecase.GetMediaImagesFromDeviceUseCase
import app.mistercooper.social.ui.feature.publish.model.PublishUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PublishViewModel @Inject constructor(
    private val getMediaImagesFromDeviceUseCase: GetMediaImagesFromDeviceUseCase,
    private val getImageFromUriUseCase: GetImageFromUriUseCase,
) : ViewModel() {
    private val _publishUiModelState = MutableStateFlow(
        PublishUiModel()
    )
    val publishUiModelState = _publishUiModelState.asStateFlow()

    fun getLocalMediaImages() {
        viewModelScope.launch {
            getMediaImagesFromDeviceUseCase()
                .onStart {
                    _publishUiModelState.emit(PublishUiModel(isLoadingLocalImages = true))
                }
                .catch {
                    it.printStackTrace()
                    _publishUiModelState.emit(PublishUiModel(errorOnLoadingLocalImages = true))

                }.collect { response ->
                    _publishUiModelState.emit(PublishUiModel(localImages = response))
                }
        }
    }

    fun onPhotoPickerSelected(photo: Uri?) {
        viewModelScope.launch {
            photo?.let {
                getImageFromUriUseCase(photo).collect {
                    _publishUiModelState.emit(PublishUiModel(selectedPhoto = it))
                }
            }?:
            _publishUiModelState.emit(PublishUiModel(errorOnLoadingLocalImages = true))
        }
    }

}