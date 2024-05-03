package app.mistercooper.social.ui.home

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
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import app.mistercooper.social.R
import app.mistercooper.social.domain.feature.home.model.Post
import app.mistercooper.social.ui.comment.CommentsBottomSheet
import app.mistercooper.social.ui.common.UserMiniatureComponent
import app.mistercooper.social.ui.home.mock.mockPosts
import app.mistercooper.social.ui.theme.SocialCooperAndroidTheme
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(posts: List<Post>?) {
    posts?.let { posts ->
        LazyColumn {
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
                            UserMiniatureComponent()
                            Text(
                                text = "Nombre Usuario",
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(8.dp)
                            )
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
                    Row {
                        Icon(
                            imageVector = Icons.Rounded.FavoriteBorder,
                            contentDescription = stringResource(id = R.string.like),
                            modifier = Modifier.padding(4.dp)
                        )
                        var showSheet by remember { mutableStateOf(false) }

                        if (showSheet) {
                            CommentsBottomSheet(post.id, post.totalComments, true) {
                                showSheet = false
                            }
                        }
                        Icon(
                            Icons.Rounded.MailOutline,
                            contentDescription = stringResource(id = R.string.comment),
                            modifier = Modifier
                                .padding(4.dp)
                                .clickable {
                                    showSheet = true }
                        )

                    }
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SocialCooperAndroidTheme {
        HomeScreen(
            mockPosts()
        )
    }
}