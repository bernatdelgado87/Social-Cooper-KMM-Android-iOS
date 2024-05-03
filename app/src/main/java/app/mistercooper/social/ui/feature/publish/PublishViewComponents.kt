package app.mistercooper.social.ui.feature.publish

import android.Manifest
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.mistercooper.social.BuildConfig
import app.mistercooper.social.R
import app.mistercooper.social.ui.common.utils.checkCustomPermission
import app.mistercooper.social.ui.common.utils.createImageFile
import app.mistercooper.social.ui.common.utils.requestCameraPermission
import app.mistercooper.social.ui.feature.publish.viewmodel.PublishViewModel
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import java.util.Objects

@Composable
fun PublishHomeScreen(navController: NavController) {

    val coroutineScope = rememberCoroutineScope()
    val viewModel = hiltViewModel<PublishViewModel>()
    val state = viewModel.publishUiModelState.collectAsState()

    var initCamera by remember {
        mutableStateOf(false)
    }

    val pickImage = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia(),
        viewModel::onPhotoPickerSelected
    )

    if (initCamera) {
        CameraLauncherComponent()
    }

    Column {
        Row() {
            Button(onClick = {
                viewModel.getLocalMediaImages()
                coroutineScope.launch {
                    pickImage.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }
            }) {
                Text("Get from local")
            }

            Button(onClick = {
                initCamera = true
            }
            ) {
                Text("Get from Camera")
            }
        }

        state.value.selectedPhoto?.apply {
            AsyncImage(
                model = this,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            initCamera = false
        }
    }


}

@Composable
fun CameraLauncherComponent() {
    val viewModel = hiltViewModel<PublishViewModel>()

    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        BuildConfig.APPLICATION_ID + ".provider",
        file
    )

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            capturedImageUri = uri
        }

    val permissionLauncher = requestCameraPermission(
        onPermissionGranted = { cameraLauncher.launch(uri) },
        onPermissionDenied = {
            Toast.makeText(
                context,
                context.getString(R.string.permission_denied), Toast.LENGTH_SHORT
            ).show()
        })

    if (capturedImageUri.path?.isNotEmpty() == true) {
        viewModel.onPhotoPickerSelected(capturedImageUri)
    }
    LaunchedEffect(Unit) {
        context.checkCustomPermission({ cameraLauncher.launch(uri) }, {
            permissionLauncher.launch(
                Manifest.permission.CAMERA
            )
        })
    }
}