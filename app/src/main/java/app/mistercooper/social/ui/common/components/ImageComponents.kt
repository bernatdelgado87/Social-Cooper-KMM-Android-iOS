package app.mistercooper.social.ui.common.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun UserMiniatureComponent() {
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
}