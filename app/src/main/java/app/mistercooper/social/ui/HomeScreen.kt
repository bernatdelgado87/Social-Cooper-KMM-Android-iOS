package app.mistercooper.social.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.mistercooper.social.R
import app.mistercooper.social.domain.feature.home.model.Post
import app.mistercooper.social.ui.theme.SocialCooperAndroidTheme
import coil.compose.AsyncImage

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
                            AsyncImage(
                                model = "https://fastly.picsum.photos/id/40/4106/2806.jpg?hmac=MY3ra98ut044LaWPEKwZowgydHZ_rZZUuOHrc3mL5mI",
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .border(2.dp, Color.Gray, CircleShape)
                                    .height(40.dp)
                                    .width(40.dp)
                            )
                            Text(
                                text = "Nombre Usuario",
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                    if (post.numberOfLikes > 0) {
                        Text(
                            text = stringResource(
                                id = R.string.post_like_people,
                                post.numberOfLikes
                            ),
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                    Row {
                        Icon(
                            Icons.Rounded.FavoriteBorder,
                            contentDescription = stringResource(id = R.string.like),
                            modifier = Modifier.padding(4.dp)
                        )
                        Icon(
                            Icons.Rounded.MailOutline,
                            contentDescription = stringResource(id = R.string.comment),
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                    Text(text = post.description,
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