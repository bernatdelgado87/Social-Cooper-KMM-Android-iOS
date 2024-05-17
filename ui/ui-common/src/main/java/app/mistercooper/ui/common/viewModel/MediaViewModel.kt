package app.mistercooper.ui.common.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mistercooper.domain.common.feature.media.usecase.GetImageFromUriUseCase
import app.mistercooper.domain.common.feature.media.usecase.GetMediaImagesFromDeviceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MediaViewModel @Inject constructor(
    private val getMediaImagesFromDeviceUseCase: GetMediaImagesFromDeviceUseCase,
    private val getImageFromUriUseCase: GetImageFromUriUseCase,
) : ViewModel() {
    private val _mediaUiModelState = MutableStateFlow(
        MediaUiModel()
    )
    val mediaUiModelState = _mediaUiModelState.asStateFlow()

    var selectedFile: MutableStateFlow<File?> = MutableStateFlow(null)

    fun getLocalMediaImages() {
        viewModelScope.launch {
            getMediaImagesFromDeviceUseCase()
                .catch {
                    it.printStackTrace()
                    _mediaUiModelState.emit(MediaUiModel(error = true))
                }
        }
    }

    fun onPhotoSelected(photo: Uri?) {
        viewModelScope.launch {
            photo?.let {
                        getImageFromUriUseCase(
                                photo.toString()
                        ).collect { file ->
                            selectedFile.emit(file)
                        }
            } ?: _mediaUiModelState.emit(MediaUiModel(error = true))
        }
    }

}