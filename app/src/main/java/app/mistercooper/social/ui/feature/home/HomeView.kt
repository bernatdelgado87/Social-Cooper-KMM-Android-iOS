package app.mistercooper.social.ui.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.MailOutline
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.mistercooper.social.R
import app.mistercooper.social.domain.feature.home.model.PostModel
import app.mistercooper.social.ui.common.components.CommonScaffoldBottomBar
import app.mistercooper.social.ui.common.components.LoadingComponent
import app.mistercooper.social.ui.common.components.UserMiniatureComponent
import app.mistercooper.social.ui.common.navigation.NavigationRoute
import app.mistercooper.social.ui.common.navigation.navigate
import app.mistercooper.social.ui.feature.comment.CommentsBottomSheet
import app.mistercooper.social.ui.feature.home.mock.mockPosts
import app.mistercooper.social.ui.feature.home.viewmodel.HomeViewModel
import app.mistercooper.social.ui.theme.SocialCooperAndroidTheme
import coil.compose.AsyncImage

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel= hiltViewModel<HomeViewModel>()
    val state = viewModel.homeUiModel.collectAsState()

    CommonScaffoldBottomBar(
        navController = navController,
        content = { modifier ->
            HomeFeedView(modifier = modifier, postModels = state.value.postModels)
            if (state.value.isLoading) {
                LoadingComponent()
            }
            if (state.value.isError) {
                ErrorComponent()
            }
        },
        showError = state.value.isError,
        actionFloatingButton = {
            navController.navigate(NavigationRoute.PUBLISH_HOME)
        },
        iconVectorFloatingButton = Icons.Rounded.Add
    )
}

@Composable
fun ErrorComponent() {
    Text(
        text = "Is Error",
    )
}

@Composable
fun HomeFeedView(postModels: List<PostModel>?, modifier: Modifier = Modifier) {
    postModels?.let { posts ->
        LazyColumn(modifier) {
            items(posts) { post ->
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        AsyncImage(
                            model = post.imageUrl,
                            "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            UserComponent()
                        }
                    }
                    if (post.totalLikes > 0) {
                        Text(
                            text = stringResource(
                                id = R.string.post_like_people,
                                post.totalLikes
                            ),
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                    InteractionIconsComponent(post)
                    Text(
                        text = post.description.orEmpty(),
                        modifier = Modifier.padding(4.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun UserComponent() {
    UserMiniatureComponent()
    Text(
        text = "Nombre Usuario",
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun InteractionIconsComponent(post: PostModel) {
    Row {
        Icon(
            imageVector = Icons.Rounded.FavoriteBorder,
            contentDescription = stringResource(id = R.string.like),
            modifier = Modifier.padding(4.dp)
        )
        var showSheet by remember { mutableStateOf(false) }

        if (showSheet) {
            CommentsBottomSheet(
                postId = post.id,
                totalComments = post.totalComments,
                writeNow = true
            ) {
                showSheet = false
            }
        }
        Icon(
            Icons.Rounded.MailOutline,
            contentDescription = stringResource(id = R.string.comment),
            modifier = Modifier
                .padding(4.dp)
                .clickable {
                    showSheet = true
                }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun HomeFeedPreview() {
    SocialCooperAndroidTheme {
        HomeFeedView(
            mockPosts()
        )
    }
}