package app.mistercooper.social.ui.feature.publish

import android.Manifest
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.mistercooper.social.BuildConfig
import app.mistercooper.social.R
import app.mistercooper.social.ui.common.components.CommonScaffoldTopBar
import app.mistercooper.social.ui.common.components.CustomTextField
import app.mistercooper.social.ui.common.components.LoadingComponent
import app.mistercooper.social.ui.common.utils.checkCustomPermission
import app.mistercooper.social.ui.common.utils.createImageFile
import app.mistercooper.social.ui.common.utils.requestCameraPermission
import app.mistercooper.social.ui.feature.publish.viewmodel.PublishViewModel
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import java.util.Objects

@Composable
fun PublishHomeScreen(navController: NavController) {
    var canActivateButton by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    val viewModel = hiltViewModel<PublishViewModel>()
    val state = viewModel.publishUiModelState.collectAsState()
    if (state.value.postPublishedSuccess){
        navController.popBackStack()
        viewModel.clearState()
    }
    CommonScaffoldTopBar(
        navController = navController,
        topBarTitle = stringResource(id = R.string.publish_now_title),
        content = { modifier ->
            PublishSelectPhotoContentView(
                modifier = modifier,
                { canActivateButton = it },
                { newText -> text = newText }
            )
            if (state.value.loading){
                LoadingComponent(modifier)
            }
        },
        showError = state.value.error,
        actions = {
            Row() {
                Button(modifier = Modifier,
                    enabled = canActivateButton,
                    onClick = {
                        viewModel.publishPost(text)
                    }) {
                    Text(text = stringResource(id = R.string.next))
                }
            }
        })
}


@Composable
fun PublishSelectPhotoContentView(
    modifier: Modifier,
    canActivateButton: (canActivateButton: Boolean) -> Unit,
    onTextChanged: (text: String) -> Unit
) {

    val viewModel = hiltViewModel<PublishViewModel>()
    val state = viewModel.publishUiModelState.collectAsState()
    var text by remember { mutableStateOf("") }
    var showSheetSourceSelection by remember { mutableStateOf(false) }

    if (showSheetSourceSelection) {
        SelectSourceBottomSheet() {
            showSheetSourceSelection = false
        }
    }
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.7f)
                    .padding(20.dp)
                    .background(color = Color.Transparent),
            ) {
                viewModel.selectedFile.collectAsState().value?.apply {
                    AsyncImage(
                        modifier = Modifier
                            .clickable { showSheetSourceSelection = true },
                        model = this,
                        contentDescription = stringResource(R.string.your_photo_content_description),
                        contentScale = ContentScale.Crop
                    )
                    canActivateButton(true)
                } ?: Column(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.white),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .fillMaxSize()
                        .align(Alignment.Center)
                        .clickable { showSheetSourceSelection = true },
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        text = "Agrega tu foto aquí",
                        color = colorResource(id = R.color.grey),
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Icon(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .padding(vertical = 20.dp),
                        painter = painterResource(R.drawable.ic_add_photo),
                        contentDescription = stringResource(id = R.string.add_photo_content_description)
                    )
                    canActivateButton(false)
                }
            }
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.BottomStart, unbounded = false)
                    .padding(horizontal = 8.dp),
                onTextChanged = { newText ->
                    text = newText
                    onTextChanged(newText)},
                showKeyboard = false,
                stringResource(id = R.string.post_enter_text_hint),
                singleLine = false
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectSourceBottomSheet(
    onDismiss: () -> Unit
) {
    val viewModel = hiltViewModel<PublishViewModel>()

    var launchCamera by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val pickImage = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) {
        viewModel.onPhotoSelected(it)
        onDismiss()
    }

    if (launchCamera) {
        CameraLauncherComponent {
            launchCamera = false
            onDismiss()
        }
    }

    ModalBottomSheet(
        modifier = Modifier
            .fillMaxWidth(),
        onDismissRequest = { onDismiss() },
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Box(

        ) {
            Column(
                modifier = Modifier.fillMaxHeight(0.3f)
            ) {

                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    text = stringResource(R.string.image_selection_title),
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    text = stringResource(R.string.image_selection_body),
                    style = MaterialTheme.typography.bodySmall
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(10.dp),
                        onClick = {
                            launchCamera = true
                        }) {
                        Icon(
                            modifier = Modifier
                                .height(20.dp)
                                .padding(horizontal = 10.dp),
                            painter = painterResource(R.drawable.ic_camera),
                            contentDescription = stringResource(id = R.string.add_photo_content_description)
                        )
                        Text(text = stringResource(R.string.publish_camera_option))
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(10.dp),
                        onClick = {
                            viewModel.getLocalMediaImages()
                            coroutineScope.launch {
                                pickImage.launch(
                                    PickVisualMediaRequest(
                                        ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )
                            }
                        }) {
                        Icon(
                            modifier = Modifier
                                .height(20.dp)
                                .padding(horizontal = 10.dp),
                            painter = painterResource(R.drawable.ic_add_photo),
                            contentDescription = stringResource(id = R.string.add_photo_content_description)
                        )
                        Text(text = stringResource(R.string.publish_files_option))
                    }
                }
            }
        }
    }
}

@Composable
fun CameraLauncherComponent(onPhotoProcessFinished: () -> Unit) {
    val viewModel = hiltViewModel<PublishViewModel>()
    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        BuildConfig.APPLICATION_ID + ".provider",
        file
    )
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { isPhotoTaken ->
            if (isPhotoTaken) {
                capturedImageUri = uri
            } else {
                onPhotoProcessFinished()
            }
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
        viewModel.onPhotoSelected(capturedImageUri)
        onPhotoProcessFinished()
    }
    LaunchedEffect(Unit) {
        context.checkCustomPermission({ cameraLauncher.launch(uri) }, {
            permissionLauncher.launch(
                Manifest.permission.CAMERA
            )
        })
    }
}