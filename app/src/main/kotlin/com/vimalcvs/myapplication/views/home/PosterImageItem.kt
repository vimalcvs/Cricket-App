package com.vimalcvs.myapplication.views.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vimalcvs.myapplication.R
import com.vimalcvs.myapplication.data.Offer


@Composable
fun PosterHorizontalItem(post: Offer) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(18.dp),
                clip = true,
                spotColor = Color.Blue.copy(alpha = 0.5f)
            )
            .background(Color.White)
            .clickable {
                Toast
                    .makeText(context, "Coming Soon", Toast.LENGTH_SHORT)
                    .show()
            }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(post.offer_banner_url)
                .build(),
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxSize()
                .heightIn(max = 120.dp)

        )
    }
}
