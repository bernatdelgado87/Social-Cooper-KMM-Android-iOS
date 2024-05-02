@file:OptIn(ExperimentalMaterial3Api::class)

package app.mistercooper.social.ui.comment

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.mistercooper.social.R
import app.mistercooper.social.domain.feature.home.model.Comment
import app.mistercooper.social.ui.comment.viewmodel.CommentViewModel
import app.mistercooper.social.ui.common.CustomTextField
import app.mistercooper.social.ui.common.UserMiniatureComponent
import app.mistercooper.social.ui.common.toPx
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CommentsBottomSheet(postId: Long, comments: List<Comment>?, writeNow: Boolean, onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    var modalHeight by remember { mutableStateOf(0) }

    ModalBottomSheet(
        modifier = Modifier
            .padding(0.dp)
            .onGloballyPositioned {
                modalHeight = it.size.height
            }
            .imePadding()
            .fillMaxSize(),
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (comments.isNullOrEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.8f)
                        .align(Alignment.Center)

                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = stringResource(R.string.comments_empty_title),
                        style = MaterialTheme.typography.headlineLarge,
                    )

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 20.dp),
                        text = stringResource(R.string.comments_empty_body),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium,
                    )
                }
            } else {
                //TODO lazyColumn with comments
            }
            CommentsFooterComponent(postId, modalHeight, modalBottomSheetState, writeNow)
        }
    }
}

@Composable
fun CommentsFooterComponent(
    postId: Long,
    modalHeight: Int,
    modalBottomSheetState: SheetState,
    showKeyboard: Boolean
) {
    val viewModel = hiltViewModel<CommentViewModel>()
    val viewModelState = viewModel.commentUiModel.collectAsState()

    var footerHeight by remember { mutableStateOf(0) }
    val bottomPadding = ButtonDefaults.MinHeight.toPx()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .offset {
                IntOffset(
                    0,
                    (modalHeight - modalBottomSheetState.requireOffset() - footerHeight).toInt()
                )
            }
            .onGloballyPositioned {
                footerHeight = (((it.size.height * 1)) + bottomPadding + 0).toInt()
            }
            .padding(vertical = 30.dp)
    ) {
        UserMiniatureComponent()
        var commentText by remember { mutableStateOf("") }
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(horizontal = 8.dp)
                .height(40.dp),
            onTextChanged = { newText -> commentText = newText },
            showKeyboard,
            stringResource(id = R.string.comment_hint),
            singleLine = false
        )
        if(viewModelState.value.isLoading) {
            AnimatedLoadingPublishComment()
        }else {
            Icon(
                Icons.Rounded.Send,
                contentDescription = stringResource(id = R.string.comment),
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
                    .clickable {
                        viewModel.publishComment(commentText, postId)
                    }
            )
        }
    }
}

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun RowScope.AnimatedLoadingPublishComment() {
    val image = AnimatedImageVector.animatedVectorResource(R.drawable.ic_timer_animated)
    var atEnd by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {

        scope.launch {
            while (true) {
                delay(300)
                atEnd = !atEnd
            }
        }
    }
    Image(
        painter = rememberAnimatedVectorPainter(image, atEnd),
        contentDescription = "Timer",
        modifier = Modifier
            .padding(4.dp)
            .align(Alignment.CenterVertically),
        contentScale = ContentScale.Crop
    )

}

