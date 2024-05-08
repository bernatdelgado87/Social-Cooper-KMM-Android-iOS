package app.mistercooper.social.ui.feature.publish

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.mistercooper.social.R
import app.mistercooper.social.ui.common.components.CommonScaffoldTopBar
import app.mistercooper.social.ui.common.components.CustomTextField
import app.mistercooper.social.ui.common.components.LoadingComponent
import app.mistercooper.social.ui.common.components.SelectSourceBottomSheet
import app.mistercooper.social.ui.common.utils.restartMainActivity
import app.mistercooper.social.ui.common.viewModel.MediaViewModel
import app.mistercooper.social.ui.feature.publish.viewmodel.PublishViewModel
import coil.compose.AsyncImage

@Composable
fun PublishHomeScreen(navController: NavController) {
    var canActivateButton by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    val publishViewModel = hiltViewModel<PublishViewModel>()
    val publishState = publishViewModel.publishUiModelState.collectAsState()

    val mediaViewModel = hiltViewModel<MediaViewModel>()

    if (publishState.value.postPublishedSuccess) {
        val context = LocalContext.current
        context.restartMainActivity()
    }
    CommonScaffoldTopBar(
        navController = navController,
        topBarTitle = stringResource(id = R.string.publish_now_title),
        content = { modifier ->
            PublishPostContentView(
                modifier = modifier,
                { canActivateButton = it },
                { newText -> text = newText }
            )
            if (publishState.value.loading) {
                LoadingComponent(modifier)
            }
        },
        showError = publishState.value.error,
        actions = {
            Row {
                Button(modifier = Modifier,
                    enabled = canActivateButton,
                    onClick = {
                        mediaViewModel.selectedFile.value?.let { file ->
                            publishViewModel.publishPost(text, file)
                        }
                    }) {
                    Text(text = stringResource(id = R.string.publish_action_button))
                }
            }
        })
}


@Composable
fun PublishPostContentView(
    modifier: Modifier,
    canActivateButton: (canActivateButton: Boolean) -> Unit,
    onTextChanged: (text: String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            SelectMediaComponent({ isFileSelected ->  canActivateButton(isFileSelected) })
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.BottomStart, unbounded = false)
                    .padding(horizontal = 8.dp),
                onTextChanged = { newText ->
                    text = newText
                    onTextChanged(newText)
                },
                showKeyboard = false,
                stringResource(id = R.string.post_enter_text_hint),
                singleLine = false
            )
        }
    }
}

@Composable
fun SelectMediaComponent(onFileSelected: (isFileSelected :Boolean) -> Unit) {
    val mediaViewModel = hiltViewModel<MediaViewModel>()
    var showSheetSourceSelection by remember { mutableStateOf(false) }

    if (showSheetSourceSelection) {
        SelectSourceBottomSheet {
            showSheetSourceSelection = false
        }
    }
    Box(
        modifier = Modifier
            .fillMaxHeight(0.7f)
            .padding(20.dp)
            .background(color = Color.Transparent),
    ) {
        mediaViewModel.selectedFile.collectAsState().value?.apply {
            AsyncImage(
                modifier = Modifier
                    .clickable { showSheetSourceSelection = true },
                model = this,
                contentDescription = stringResource(R.string.your_photo_content_description),
                contentScale = ContentScale.Crop
            )
            onFileSelected(true)
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
                text = stringResource(R.string.add_photo_hint),
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
            onFileSelected(false)
        }
    }
}